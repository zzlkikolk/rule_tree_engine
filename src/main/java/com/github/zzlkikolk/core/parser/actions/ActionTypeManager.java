package com.github.zzlkikolk.core.parser.actions;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.zzlkikolk.core.model.actions.Actions;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

public class ActionTypeManager {

    private static final String BASE_PACKAGE = "com.github.zzlkikolk.core.model.actions";


    // 初始化时自动扫描注册
    static {
        scanAndRegisterSubtypes();
    }

    public static void init() {
        scanAndRegisterSubtypes();
    }

    /**
     * 自动扫描内置包路径下的子类
     */
    private static void scanAndRegisterSubtypes() {
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);

        scanner.addIncludeFilter(new AnnotationTypeFilter(JsonTypeName.class));
        scanner.addIncludeFilter((metadataReader, factory) -> {
            try {
                Class<?> clazz = ClassUtils.forName(metadataReader.getClassMetadata().getClassName(), null);
                return Actions.class.isAssignableFrom(clazz);
            } catch (ClassNotFoundException e) {
                return false;
            }
        });

        for (BeanDefinition candidate : scanner.findCandidateComponents(BASE_PACKAGE)) {
            registerCandidate(candidate);
        }
    }

    // 运行时动态注册新类型
    public static void registerAction(String typeName, Class<? extends Actions> actionClass) {
        ActionTypeRegistry.register(typeName, actionClass);
    }


    /**
     * 扫描指定包路径下所有带 @JsonTypeName 的 Actions 子类
     */
    public static void scanPackages(String... basePackages) {
        // 创建扫描器（不使用默认过滤器）
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);

        // 添加自定义过滤器：必须是 Actions 的子类且带 @JsonTypeName 注解
        scanner.addIncludeFilter(new AnnotationTypeFilter(JsonTypeName.class));
        scanner.addIncludeFilter((metadataReader, factory) -> {
            try {
                Class<?> clazz = ClassUtils.forName(metadataReader.getClassMetadata().getClassName(), null);
                return Actions.class.isAssignableFrom(clazz);
            } catch (ClassNotFoundException e) {
                return false;
            }
        });

        // 扫描所有指定的包
        for (String basePackage : basePackages) {
            for (BeanDefinition candidate : scanner.findCandidateComponents(basePackage)) {
                registerCandidate(candidate);
            }
        }
    }

    private static void registerCandidate(BeanDefinition candidate) {
        try {
            Class<?> clazz = Class.forName(candidate.getBeanClassName());
            JsonTypeName typeAnno = clazz.getAnnotation(JsonTypeName.class);
            if (typeAnno != null && Actions.class.isAssignableFrom(clazz)) {
                @SuppressWarnings("unchecked")
                Class<? extends Actions> actionClass = (Class<? extends Actions>) clazz;
                ActionTypeRegistry.register(typeAnno.value(), actionClass);
            }
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Failed to load class: " + candidate.getBeanClassName(), e);
        }
    }
}
