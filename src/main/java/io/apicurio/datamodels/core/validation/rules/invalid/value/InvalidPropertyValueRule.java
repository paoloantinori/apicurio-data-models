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

package io.apicurio.datamodels.core.validation.rules.invalid.value;

import io.apicurio.datamodels.core.validation.ValidationRule;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;

/**
 * Base class for all Invalid Property Value rules.
 * @author eric.wittmann@gmail.com
 */
public class InvalidPropertyValueRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public InvalidPropertyValueRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * Returns true if the given media type name is multipart/* or application/x-www-form-urlencoded
     * @param typeName
     */
    protected boolean isValidMultipartType(String typeName) {
        return equals(typeName, "application/x-www-form-urlencoded") || typeName.indexOf("multipart") == 0;
    }

}
