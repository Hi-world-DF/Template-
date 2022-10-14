package com.wdf.tp.rest.config;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet;
import com.wdf.tp.common.pojo.LanguageTypeEnum;
import com.wdf.tp.common.utils.RequestUtils;
import com.wdf.tp.rest.constants.ApiConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

/**
 * Swagger Config
 *
 * @author Defen Wang
 * @version 1.0, 2022/10/14 13:41
 * @since 1.0.0
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "swagger2", value = {"enable"}, havingValue = "true")
public class SwaggerConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html", "doc.html")
            .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * v1.0.0 Api
     *
     * @return docket
     */
    @Bean
    public Docket templateApi() {
        return createDocket(ApiConstant.TITLE, ApiConstant.CONTROLLER, null);
    }

    /**
     * 创建接口文档
     *
     * @param groupName   分组名
     * @param basePackage 包名
     * @param version     版本
     * @return docket
     */
    private Docket createDocket(String groupName, String basePackage, String version) {
        return new Docket(DocumentationType.SWAGGER_2)
            .directModelSubstitute(LocalDateTime.class, Date.class)
            .directModelSubstitute(LocalDate.class, String.class)
            .directModelSubstitute(LocalTime.class, String.class)
            .directModelSubstitute(ZonedDateTime.class, String.class)
            .directModelSubstitute(Long.class, String.class)
            .ignoredParameterTypes(LanguageTypeEnum.class)
            .consumes(ImmutableSet.of(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
            .produces(ImmutableSet.of(MediaType.APPLICATION_JSON_VALUE))
            .groupName(groupName)
            .apiInfo(new ApiInfoBuilder()
                .title("Api Doc")
                .version(version)
                .description("所有提交采用form表单，所有返回json格式，不转换参数风格，即不做snake_case和camelCase的转换。\n\n" +
                    "所有返回值采用以下格式:\n{\"success\":true,\"errorCode\":0,\"data\":\"\",\"errorMsg\":\"OK\"}\n\n" +
                    "接口规范参考gmail api,资源路径为users/{uid}/业务域/{业务id},未登录情况下uid使用'me'")
                .build())
            .useDefaultResponseMessages(false)
            // 解决CompletionStage返回值问题
            .genericModelSubstitutes(CompletionStage.class)
            .select()
            .apis(Predicates.and(input -> input.declaringClass().getPackage().getName().equals(basePackage), matchVersion(version)))
            .paths(PathSelectors.any())
            .build()
            .globalOperationParameters(setHeaderToken())
            .securitySchemes(securitySchemes())
            .securityContexts(securityContexts());
    }

    /**
     * 匹配版本
     *
     * @param version version
     * @return predicate
     */
    private Predicate<RequestHandler> matchVersion(String version) {
        return handler -> {
            if (null == version) {
                return true;
            }
            // 方法上注解的版本
            Optional<ApiOperation> apiOperationOpt = handler.findAnnotation(ApiOperation.class);
            if (apiOperationOpt.isPresent() && Arrays.asList(apiOperationOpt.get().tags()).contains(version)) {
                return true;
            }
            // 控制器上注解的版本
            Optional<Api> apiOpt = handler.findControllerAnnotation(Api.class);
            if (apiOpt.isPresent() && Arrays.asList(apiOpt.get().tags()).contains(version)) {
                // 若方法上的版本号大于注解上的版本号，不显示
                if (apiOperationOpt.isPresent() && !max(version, apiOperationOpt.get().tags()).equals(version)) {
                    return false;
                } else {
                    return true;
                }
            }
            return false;
        };
    }

    /**
     * 获取最大版本号
     *
     * @param controllerVersion 控制器上的版本号
     * @param methodVersions    方法上的版本号
     * @return 最大版本号
     */
    public String max(String controllerVersion, String[] methodVersions) {
        return Stream.concat(Stream.of(methodVersions), Stream.of(controllerVersion))
            .max(StringUtils::compare).orElse(controllerVersion);
    }

    private List<Parameter> setHeaderToken() {
        ParameterBuilder tokenParam = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();
        Parameter lang = tokenParam.name("Accept-Language")
            .description("lang")
            .modelRef(new ModelRef("String"))
            .parameterType("header")
            .defaultValue("zh-CN")
            .required(false)
            .build();
        parameters.add(lang);
        Parameter ua = tokenParam.name("User-Agent")
            .description("User-Agent")
            .modelRef(new ModelRef("String"))
            .parameterType("header")
            .defaultValue("dvc/1.2.0+2 android/8.1.0(Redmi 5 Plus)")
            .required(false)
            .build();
        parameters.add(ua);
        Parameter locale = tokenParam.name("locale")
            .description("国际化")
            .modelRef(new ModelRef("String"))
            .parameterType("query")
            .defaultValue("en")
            .required(false)
            .build();
        parameters.add(locale);
        return parameters;
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeys = new ArrayList<>();
        apiKeys.add(new ApiKey("Authorization", "Authorization", "header"));
        apiKeys.add(new ApiKey(RequestUtils.UUID_HEADER_NAME, RequestUtils.UUID_HEADER_NAME, "header"));
        return apiKeys;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> contexts = new ArrayList<>();
        contexts.add(SecurityContext.builder()
            .securityReferences(securityReferences())
            .forPaths(PathSelectors.regex("^(?!auth).*$"))
            .build());
        return contexts;
    }

    public List<SecurityReference> securityReferences() {
        AuthorizationScope[] scopes = new AuthorizationScope[]{new AuthorizationScope("global", "")};
        SecurityReference auth = new SecurityReference("Authorization", scopes);
        SecurityReference uuid = new SecurityReference(RequestUtils.UUID_HEADER_NAME, scopes);
        List<SecurityReference> references = new ArrayList<>();
        references.add(auth);
        references.add(uuid);
        return references;
    }
}
