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

package io.apicurio.datamodels.core.validation.rules.invalid.format;

import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.validation.ValidationRule;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;
import io.apicurio.datamodels.openapi.v3.models.Oas30RequestBody;
import io.apicurio.datamodels.openapi.v3.models.Oas30RequestBodyDefinition;

/**
 * Implements the Invalid Request Body Description Rule
 * @author eric.wittmann@gmail.com
 */
public class OasInvalidRequestBodyDescriptionRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasInvalidRequestBodyDescriptionRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitRequestBody(io.apicurio.datamodels.openapi.v3.models.Oas30RequestBody)
     */
    @Override
    public void visitRequestBody(Oas30RequestBody node) {
        if (hasValue(node.description)) {
            this.reportIfInvalid(isValidCommonMark(node.description), node, Constants.PROP_DESCRIPTION, map());
        }
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitRequestBodyDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30RequestBodyDefinition)
     */
    @Override
    public void visitRequestBodyDefinition(Oas30RequestBodyDefinition node) {
        visitRequestBody(node);
    }

}
