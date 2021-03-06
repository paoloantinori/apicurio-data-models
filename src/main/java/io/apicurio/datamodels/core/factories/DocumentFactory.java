/*
 * Copyright 2019 Red Hat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.apicurio.datamodels.core.factories;

import io.apicurio.datamodels.asyncapi.models.AaiDocument;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Document;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.openapi.v2.models.Oas20Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;

/**
 * Factory used to create documents.
 * @author eric.wittmann@gmail.com
 */
public class DocumentFactory {
    
    public static final Document create(DocumentType type) {
        switch (type) {
            case asyncapi2: {
                AaiDocument doc = new Aai20Document();
                doc.asyncapi = "2.0.0";
                return doc;
            }
            case openapi2: {
                Oas20Document doc = new Oas20Document();
                return doc;
            }
            case openapi3: {
                Oas30Document doc = new Oas30Document();
                doc.openapi = "3.0.2";
                return doc;
            }
            default:
                throw new RuntimeException("Failed to create a Document for type: " + type);
            
        }
    }

}
