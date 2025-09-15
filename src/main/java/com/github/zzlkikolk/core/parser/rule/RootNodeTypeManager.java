package com.github.zzlkikolk.core.parser.rule;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.zzlkikolk.core.model.rule.RootNode;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

public class RootNodeTypeManager {

    private static final String BASE_PACKAGE = "com.github.zzlkikolk.core.model.rule";


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
                return RootNode.class.isAssignableFrom(clazz);
            } catch (ClassNotFoundException e) {
                return false;
            }
        });

        for (BeanDefinition candidate : scanner.findCandidateComponents(BASE_PACKAGE)) {
            registerCandidate(candidate);
        }
    }

    /**
     * 手动注册
     *
     * @param typeName
     * @param rootNodeClass
     */
    // 运行时动态注册新类型
    public static void registerRootNode(String typeName, Class<? extends RootNode> rootNodeClass) {
        RootNodeTypeRegistry.register(typeName, rootNodeClass);
    }


    /**
     * 扫描指定包路径下所有带 @JsonTypeName 的 RootNode 子类
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
                return RootNode.class.isAssignableFrom(clazz);
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
            if (typeAnno != null && RootNode.class.isAssignableFrom(clazz)) {
                @SuppressWarnings("unchecked")
                Class<? extends RootNode> rootNodeClass = (Class<? extends RootNode>) clazz;
                RootNodeTypeRegistry.register(typeAnno.value(), rootNodeClass);
            }
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Failed to load class: " + candidate.getBeanClassName(), e);
        }
    }
}
