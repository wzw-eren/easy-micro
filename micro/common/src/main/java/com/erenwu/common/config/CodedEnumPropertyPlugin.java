package com.erenwu.common.config;

import com.erenwu.common.enums.BaseEnum;
import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.google.common.base.Optional;

import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.ModelPropertyBuilder;
import springfox.documentation.schema.Annotations;
import springfox.documentation.service.AllowableListValues;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.swagger.schema.ApiModelProperties;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 枚举处理
 * @author eren
 */
@Slf4j
public class CodedEnumPropertyPlugin implements ModelPropertyBuilderPlugin {
	/**
	 * Implement this method to override the @see springfox.documentation.schema.ModelProperty using the @see springfox
	 * .documentation.builders.ModelPropertyBuilder
	 *
	 * @param context - context that can be used to override the model property attributes
	 */
	@Override
	public void apply(ModelPropertyContext context) {

		Optional<ApiModelProperty> annotation = Optional.absent();

		if (context.getAnnotatedElement().isPresent()) {
			annotation = annotation.or(ApiModelProperties.findApiModePropertyAnnotation(context.getAnnotatedElement().get()));
		}
		BeanPropertyDefinition beanPropertyDefinition;
		if (!context.getBeanPropertyDefinition().isPresent()) {
			log.warn("当前BeanPropertyDefinition未准备好");
			return;
		}
		beanPropertyDefinition = context.getBeanPropertyDefinition().get();
		annotation = annotation.or(Annotations.findPropertyAnnotation(beanPropertyDefinition, ApiModelProperty.class));
		final Class<?> rawPrimaryType = beanPropertyDefinition.getRawPrimaryType();
		//过滤得到目标类型
		if (annotation.isPresent() && BaseEnum.class.isAssignableFrom(rawPrimaryType)) {
			String className = rawPrimaryType.getName();
			//获取CodedEnum的code值
			BaseEnum[] values = (BaseEnum[]) rawPrimaryType.getEnumConstants();
			final List<String> displayValues = Arrays.stream(values)
					.map(codedEnum -> {
						if(codedEnum instanceof Enum){
							return  ((Enum<?>) codedEnum).name() + " - " + codedEnum.getDisplayName();
						}
						return  codedEnum.getValue() + " - " + codedEnum.getDisplayName();
					})
					.collect(Collectors.toList());
			final AllowableListValues allowableListValues = new AllowableListValues(displayValues, rawPrimaryType.getTypeName());

			final ResolvedType resolvedType = context.getResolver().resolve(String.class);
			context.getBuilder().allowableValues(allowableListValues).type(resolvedType);
			try {
				Field mField = ModelPropertyBuilder.class.getDeclaredField("description");
				mField.setAccessible(true);
				className = mField.get(context.getBuilder()) + ":"+className;
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			context.getBuilder().description(className);

		}
	}

	@Override
	public boolean supports(DocumentationType documentationType) {
		return true;
	}
}
