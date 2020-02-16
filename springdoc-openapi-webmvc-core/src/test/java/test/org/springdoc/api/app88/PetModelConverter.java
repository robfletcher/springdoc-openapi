package test.org.springdoc.api.app88;

import java.util.Iterator;
import com.fasterxml.jackson.databind.type.SimpleType;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.oas.models.media.ComposedSchema;
import io.swagger.v3.oas.models.media.Schema;
import org.springframework.stereotype.Component;

@Component
public class PetModelConverter implements ModelConverter {
  @Override
  public Schema<?> resolve(
    AnnotatedType annotatedType,
    ModelConverterContext modelConverterContext,
    Iterator<ModelConverter> iterator) {
    if (annotatedType.getType() instanceof SimpleType && ((SimpleType)annotatedType.getType()).getRawClass().equals(PetController.Pet.class)) {
      modelConverterContext.defineModel("Cat", modelConverterContext.resolve(new AnnotatedType(PetController.Cat.class)));
      modelConverterContext.defineModel("Dog", modelConverterContext.resolve(new AnnotatedType(PetController.Dog.class)));
      return new ComposedSchema()
        .addOneOfItem(new Schema<>().$ref("#/components/schemas/Cat"))
        .addOneOfItem(new Schema<>().$ref("#/components/schemas/Dog"));
    } else {
      return iterator.next().resolve(annotatedType, modelConverterContext, iterator);
    }
  }
}
