package test.org.springdoc.api.app88;

import java.util.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("pet")
public class PetController {

  @PutMapping("/{petRecordId}")
  @Operation
  public ResponseEntity<PetController.PetRecord<?>> putPetRecord(
    @PathVariable UUID petRecordId,
    @RequestBody PetController.PetRecord<?> petRecord
  ) {
    return ResponseEntity.ok(petRecord);
  }

  static class PetRecord<T extends Pet> {
    private final String owner;
    private final T pet;

    public PetRecord(String owner, T pet) {
      this.owner = owner;
      this.pet = pet;
    }

    public String getOwner() {
      return owner;
    }

    public T getPet() {
      return pet;
    }
  }

  interface Pet {
    String getName();
  }

  static class Cat implements Pet {
    private final String name;

    Cat(String name) {this.name = name;}

    @Override public String getName() {
      return name;
    }
  }

  static class Dog implements Pet {
    private final String name;

    Dog(String name) {this.name = name;}

    @Override public String getName() {
      return name;
    }
  }
}
