package com.epam.ivanou4.vechicle.validation;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import java.io.IOException;

public class Validation {

    public Boolean validate(String jsonString, String schemaName) {
        try {
            JsonNode jsonNode = JsonLoader.fromString(jsonString);
            JsonNode jsonSchema = JsonLoader.fromResource("/schema/" + schemaName);
            JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
            JsonSchema schema = factory.getJsonSchema(jsonSchema);
            return schema.validInstance(jsonNode);
        } catch (IOException | ProcessingException e) {
            return false;
        }
    }
}
