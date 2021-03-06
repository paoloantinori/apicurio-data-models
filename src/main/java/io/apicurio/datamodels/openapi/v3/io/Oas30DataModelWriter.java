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

package io.apicurio.datamodels.openapi.v3.io;

import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.common.AuthorizationCodeOAuthFlow;
import io.apicurio.datamodels.core.models.common.ClientCredentialsOAuthFlow;
import io.apicurio.datamodels.core.models.common.Components;
import io.apicurio.datamodels.core.models.common.IDefinition;
import io.apicurio.datamodels.core.models.common.IExample;
import io.apicurio.datamodels.core.models.common.ImplicitOAuthFlow;
import io.apicurio.datamodels.core.models.common.OAuthFlow;
import io.apicurio.datamodels.core.models.common.OAuthFlows;
import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.core.models.common.PasswordOAuthFlow;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.core.models.common.ServerVariable;
import io.apicurio.datamodels.openapi.io.OasDataModelWriter;
import io.apicurio.datamodels.openapi.models.OasHeader;
import io.apicurio.datamodels.openapi.models.OasPathItem;
import io.apicurio.datamodels.openapi.models.OasResponse;
import io.apicurio.datamodels.openapi.v3.models.Oas30Callback;
import io.apicurio.datamodels.openapi.v3.models.Oas30CallbackDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30CallbackPathItem;
import io.apicurio.datamodels.openapi.v3.models.Oas30Discriminator;
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;
import io.apicurio.datamodels.openapi.v3.models.Oas30Encoding;
import io.apicurio.datamodels.openapi.v3.models.Oas30Example;
import io.apicurio.datamodels.openapi.v3.models.Oas30ExampleDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30Header;
import io.apicurio.datamodels.openapi.v3.models.Oas30HeaderDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30Link;
import io.apicurio.datamodels.openapi.v3.models.Oas30LinkDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30LinkParameterExpression;
import io.apicurio.datamodels.openapi.v3.models.Oas30LinkRequestBodyExpression;
import io.apicurio.datamodels.openapi.v3.models.Oas30LinkServer;
import io.apicurio.datamodels.openapi.v3.models.Oas30MediaType;
import io.apicurio.datamodels.openapi.v3.models.Oas30Parameter;
import io.apicurio.datamodels.openapi.v3.models.Oas30PathItem;
import io.apicurio.datamodels.openapi.v3.models.Oas30RequestBody;
import io.apicurio.datamodels.openapi.v3.models.Oas30RequestBodyDefinition;
import io.apicurio.datamodels.openapi.v3.models.Oas30Response;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30AnyOfSchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30NotSchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30OneOfSchema;
import io.apicurio.datamodels.openapi.v3.models.Oas30SecurityScheme;
import io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor;

/**
 * @author eric.wittmann@gmail.com
 */
public class Oas30DataModelWriter extends OasDataModelWriter implements IOas30Visitor {

    /**
     * @see io.apicurio.datamodels.core.io.DataModelWriter#writeDocument(io.apicurio.datamodels.core.models.Document, java.lang.Object)
     */
    @Override
    protected void writeDocument(Document node, Object json) {
        Oas30Document doc = (Oas30Document) node;
        JsonCompat.setPropertyString(json, Constants.PROP_OPENAPI, doc.openapi);
        JsonCompat.setPropertyNull(json, Constants.PROP_INFO);
        JsonCompat.setPropertyNull(json, Constants.PROP_SERVERS);
        JsonCompat.setPropertyNull(json, Constants.PROP_PATHS);
        JsonCompat.setPropertyNull(json, Constants.PROP_COMPONENTS);
        JsonCompat.setPropertyNull(json, Constants.PROP_SECURITY);
        JsonCompat.setPropertyNull(json, Constants.PROP_TAGS);
        JsonCompat.setPropertyNull(json, Constants.PROP_EXTERNAL_DOCS);
        
        writeExtraProperties(json, node);
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.io.OasDataModelWriter#writeHeader(java.lang.Object, io.apicurio.datamodels.openapi.models.OasHeader)
     */
    @Override
    protected void writeHeader(Object json, OasHeader node) {
        Oas30Header header = (Oas30Header) node;
        JsonCompat.setPropertyString(json, Constants.PROP_$REF, header.$ref);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_REQUIRED, header.required);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_DEPRECATED, header.deprecated);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_ALLOW_EMPTY_VALUE, header.allowEmptyValue);
        JsonCompat.setPropertyString(json, Constants.PROP_STYLE, header.style);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_EXPLODE, header.explode);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_ALLOW_RESERVED, header.allowReserved);
        JsonCompat.setPropertyNull(json, Constants.PROP_SCHEMA);
        JsonCompat.setProperty(json, Constants.PROP_EXAMPLE, header.example);
        JsonCompat.setPropertyNull(json, Constants.PROP_EXAMPLES);
        JsonCompat.setPropertyNull(json, Constants.PROP_CONTENT);
        super.writeHeader(json, node);
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.io.OasDataModelWriter#addHeaderToParent(java.lang.Object, java.lang.Object, io.apicurio.datamodels.openapi.models.OasHeader)
     */
    @Override
    protected void addHeaderToParent(Object parent, Object json, OasHeader node) {
        Object headers = JsonCompat.getProperty(parent, Constants.PROP_HEADERS);
        if (headers == null) {
            headers = JsonCompat.objectNode();
            JsonCompat.setProperty(parent, Constants.PROP_HEADERS, headers);
        }
        
        JsonCompat.setProperty(headers, node.getName(), json);
    }

    /**
     * @see io.apicurio.datamodels.openapi.io.OasDataModelWriter#writeOperation(java.lang.Object, io.apicurio.datamodels.core.models.common.Operation)
     */
    @Override
    protected void writeOperation(Object json, Operation node) {
        JsonCompat.setPropertyNull(json, Constants.PROP_REQUEST_BODY);
        JsonCompat.setPropertyNull(json, Constants.PROP_CALLBACKS);
        JsonCompat.setPropertyNull(json, Constants.PROP_SERVERS);
        super.writeOperation(json, node);
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.io.OasDataModelWriter#writePathItem(java.lang.Object, io.apicurio.datamodels.openapi.models.OasPathItem)
     */
    @Override
    protected void writePathItem(Object json, OasPathItem node) {
        Oas30PathItem pathItem = (Oas30PathItem) node;
        JsonCompat.setPropertyString(json, Constants.PROP_SUMMARY, pathItem.summary);
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, pathItem.description);
        JsonCompat.setPropertyNull(json, Constants.PROP_TRACE);
        JsonCompat.setPropertyNull(json, Constants.PROP_SERVERS);
        super.writePathItem(json, node);
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.io.OasDataModelWriter#writeParameter(java.lang.Object, io.apicurio.datamodels.core.models.common.Parameter)
     */
    @Override
    protected void writeParameter(Object json, Parameter node) {
        Oas30Parameter parameter = (Oas30Parameter) node;
        JsonCompat.setPropertyBoolean(json, Constants.PROP_DEPRECATED, parameter.deprecated);
        JsonCompat.setPropertyString(json, Constants.PROP_STYLE, parameter.style);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_EXPLODE, parameter.explode);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_ALLOW_RESERVED, parameter.allowReserved);
        JsonCompat.setProperty(json, Constants.PROP_EXAMPLE, parameter.example);
        JsonCompat.setPropertyNull(json, Constants.PROP_EXAMPLES);
        JsonCompat.setPropertyNull(json, Constants.PROP_CONTENT);
        super.writeParameter(json, node);
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.io.OasDataModelWriter#writeResponse(java.lang.Object, io.apicurio.datamodels.openapi.models.OasResponse)
     */
    @Override
    protected void writeResponse(Object json, OasResponse node) {
        Oas30Response response = (Oas30Response) node;
        JsonCompat.setPropertyNull(json, Constants.PROP_HEADERS);
        JsonCompat.setPropertyNull(json, Constants.PROP_CONTENT);
        JsonCompat.setPropertyNull(json, Constants.PROP_LINKS);
        super.writeResponse(json, response);
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.io.OasDataModelWriter#writeSchema(java.lang.Object, io.apicurio.datamodels.core.models.common.Schema)
     */
    @Override
    protected void writeSchema(Object json, Schema node) {
        Oas30Schema schema = (Oas30Schema) node;
        JsonCompat.setPropertyNull(json, Constants.PROP_ONE_OF);
        JsonCompat.setPropertyNull(json, Constants.PROP_ANY_OF);
        JsonCompat.setPropertyNull(json, Constants.PROP_NOT);
        JsonCompat.setPropertyNull(json, Constants.PROP_DISCRIMINATOR);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_NULLABLE, schema.nullable);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_WRITE_ONLY, schema.writeOnly);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_DEPRECATED, schema.deprecated);
        super.writeSchema(json, node);
    }
    
    /**
     * @see io.apicurio.datamodels.core.io.DataModelWriter#writeSecurityScheme(java.lang.Object, io.apicurio.datamodels.core.models.common.SecurityScheme)
     */
    @Override
    protected void writeSecurityScheme(Object json, SecurityScheme node) {
        Oas30SecurityScheme schema = (Oas30SecurityScheme) node;
        JsonCompat.setPropertyString(json, Constants.PROP_$REF, schema.$ref);
        JsonCompat.setPropertyString(json, Constants.PROP_SCHEME, schema.scheme);
        JsonCompat.setPropertyString(json, Constants.PROP_BEARER_FORMAT, schema.bearerFormat);
        JsonCompat.setPropertyNull(json, Constants.PROP_FLOWS);
        JsonCompat.setPropertyString(json, Constants.PROP_OPEN_ID_CONNECT_URL, schema.openIdConnectUrl);
        super.writeSecurityScheme(json, node);
    }

    /**
     * @see io.apicurio.datamodels.core.io.DataModelWriter#addParameterDefinitionToParent(java.lang.Object, java.lang.Object, io.apicurio.datamodels.core.models.common.IDefinition)
     */
    @Override
    protected void addParameterDefinitionToParent(Object parent, Object json, IDefinition node) {
        Object parameters = JsonCompat.getProperty(parent, Constants.PROP_PARAMETERS);
        if (parameters == null) {
            parameters = JsonCompat.objectNode();
            JsonCompat.setProperty(parent, Constants.PROP_PARAMETERS, parameters);
        }
        JsonCompat.setProperty(parameters, node.getName(), json);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitComponents(io.apicurio.datamodels.core.models.common.Components)
     */
    @Override
    public void visitComponents(Components node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        this.writeExtraProperties(json, node);

        JsonCompat.setProperty(parent, Constants.PROP_COMPONENTS, json);
        
        this.updateIndex(node, json);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitCallbackPathItem(io.apicurio.datamodels.openapi.v3.models.Oas30CallbackPathItem)
     */
    @Override
    public void visitCallbackPathItem(Oas30CallbackPathItem node) {
        this.visitPathItem(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitCallback(io.apicurio.datamodels.openapi.v3.models.Oas30Callback)
     */
    @Override
    public void visitCallback(Oas30Callback node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        writeCallback(json, node);
        this.writeExtraProperties(json, node);

        Object callbacks = JsonCompat.getProperty(parent, Constants.PROP_CALLBACKS);
        if (callbacks == null) {
            callbacks = JsonCompat.objectNode();
            JsonCompat.setProperty(parent, Constants.PROP_CALLBACKS, callbacks);
        }
        JsonCompat.setProperty(callbacks, node.getName(), json);
        
        this.updateIndex(node, json);
    }
    protected void writeCallback(Object json, Oas30Callback node) {
        JsonCompat.setPropertyString(json, Constants.PROP_$REF, node.$ref);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitLinkServer(io.apicurio.datamodels.openapi.v3.models.Oas30LinkServer)
     */
    @Override
    public void visitLinkServer(Oas30LinkServer node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        JsonCompat.setPropertyString(json, Constants.PROP_URL, node.url);
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, node.description);
        JsonCompat.setPropertyNull(json, Constants.PROP_VARIABLES);
        this.writeExtraProperties(json, node);

        JsonCompat.setProperty(parent, Constants.PROP_SERVER, json);
        
        this.updateIndex(node, json);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitCallbackDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30CallbackDefinition)
     */
    @Override
    public void visitCallbackDefinition(Oas30CallbackDefinition node) {
        this.visitCallback(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitLink(io.apicurio.datamodels.openapi.v3.models.Oas30Link)
     */
    @Override
    public void visitLink(Oas30Link node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        JsonCompat.setPropertyString(json, Constants.PROP_$REF, node.$ref);
        JsonCompat.setPropertyString(json, Constants.PROP_OPERATION_REF, node.operationRef);
        JsonCompat.setPropertyString(json, Constants.PROP_OPERATION_ID, node.operationId);
        JsonCompat.setPropertyNull(json, Constants.PROP_PARAMETERS);
        JsonCompat.setPropertyNull(json, Constants.PROP_REQUEST_BODY);
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, node.description);
        JsonCompat.setPropertyNull(json, Constants.PROP_SERVER);
        this.writeExtraProperties(json, node);

        Object links = JsonCompat.getProperty(parent, Constants.PROP_LINKS);
        if (links == null) {
            links = JsonCompat.objectNode();
            JsonCompat.setProperty(parent, Constants.PROP_LINKS, links);
        }
        JsonCompat.setProperty(links, node.getName(), json);

        this.updateIndex(node, json);        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitLinkRequestBodyExpression(io.apicurio.datamodels.openapi.v3.models.Oas30LinkRequestBodyExpression)
     */
    @Override
    public void visitLinkRequestBodyExpression(Oas30LinkRequestBodyExpression node) {
        Object parent = this.lookupParentJson(node);
        JsonCompat.setPropertyString(parent, Constants.PROP_REQUEST_BODY, node.getValue());
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitLinkParameterExpression(io.apicurio.datamodels.openapi.v3.models.Oas30LinkParameterExpression)
     */
    @Override
    public void visitLinkParameterExpression(Oas30LinkParameterExpression node) {
        Object parent = this.lookupParentJson(node);
        Object parameters = JsonCompat.getProperty(parent, Constants.PROP_PARAMETERS);
        if (parameters == null) {
            parameters = JsonCompat.objectNode();
            JsonCompat.setProperty(parent, Constants.PROP_PARAMETERS, parameters);
        }
        JsonCompat.setPropertyString(parameters, node.getName(), node.getValue());
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitLinkDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30LinkDefinition)
     */
    @Override
    public void visitLinkDefinition(Oas30LinkDefinition node) {
        this.visitLink(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitAuthorizationCodeOAuthFlow(io.apicurio.datamodels.core.models.common.AuthorizationCodeOAuthFlow)
     */
    @Override
    public void visitAuthorizationCodeOAuthFlow(AuthorizationCodeOAuthFlow node) {
        this.doVisitOAuthFlow(node, Constants.PROP_AUTHORIZATION_CODE);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitClientCredentialsOAuthFlow(io.apicurio.datamodels.core.models.common.ClientCredentialsOAuthFlow)
     */
    @Override
    public void visitClientCredentialsOAuthFlow(ClientCredentialsOAuthFlow node) {
        this.doVisitOAuthFlow(node, Constants.PROP_CLIENT_CREDENTIALS);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitPasswordOAuthFlow(io.apicurio.datamodels.core.models.common.PasswordOAuthFlow)
     */
    @Override
    public void visitPasswordOAuthFlow(PasswordOAuthFlow node) {
        this.doVisitOAuthFlow(node, Constants.PROP_PASSWORD);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitImplicitOAuthFlow(io.apicurio.datamodels.core.models.common.ImplicitOAuthFlow)
     */
    @Override
    public void visitImplicitOAuthFlow(ImplicitOAuthFlow node) {
        this.doVisitOAuthFlow(node, Constants.PROP_IMPLICIT);
    }

    /**
     * Visit an OAuth flow.
     * @param node
     * @param flowName
     */
    protected void doVisitOAuthFlow(OAuthFlow node, String flowName) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        JsonCompat.setPropertyString(json, Constants.PROP_AUTHORIZATION_URL, node.authorizationUrl);
        JsonCompat.setPropertyString(json, Constants.PROP_TOKEN_URL, node.tokenUrl);
        JsonCompat.setPropertyString(json, Constants.PROP_REFRESH_URL, node.refreshUrl);
        JsonCompat.setProperty(json, Constants.PROP_SCOPES, node.scopes);
        this.writeExtraProperties(json, node);

        JsonCompat.setProperty(parent, flowName, json);

        this.updateIndex(node, json);        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitOAuthFlows(io.apicurio.datamodels.core.models.common.OAuthFlows)
     */
    @Override
    public void visitOAuthFlows(OAuthFlows node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        JsonCompat.setPropertyNull(json, Constants.PROP_IMPLICIT);
        JsonCompat.setPropertyNull(json, Constants.PROP_PASSWORD);
        JsonCompat.setPropertyNull(json, Constants.PROP_CLIENT_CREDENTIALS);
        JsonCompat.setPropertyNull(json, Constants.PROP_AUTHORIZATION_CODE);
        this.writeExtraProperties(json, node);

        JsonCompat.setProperty(parent, Constants.PROP_FLOWS, json);

        this.updateIndex(node, json);        
    }

    /**
     * @see io.apicurio.datamodels.openapi.visitors.IOasVisitor#visitExample(io.apicurio.datamodels.core.models.common.IExample)
     */
    @Override
    public void visitExample(IExample node) {
        Oas30Example example30 = (Oas30Example) node;
        
        Object parent = this.lookupParentJson(example30);
        Object json = JsonCompat.objectNode();
        JsonCompat.setPropertyString(json, Constants.PROP_$REF, example30.$ref);
        JsonCompat.setPropertyString(json, Constants.PROP_SUMMARY, example30.summary);
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, example30.description);
        JsonCompat.setProperty(json, Constants.PROP_VALUE, example30.value);
        JsonCompat.setPropertyString(json, Constants.PROP_EXTERNAL_VALUE, example30.externalValue);
        this.writeExtraProperties(json, example30);

        Object examples = JsonCompat.getProperty(parent, Constants.PROP_EXAMPLES);
        if (examples == null) {
            examples = JsonCompat.objectNode();
            JsonCompat.setProperty(parent, Constants.PROP_EXAMPLES, examples);
        }
        JsonCompat.setProperty(examples, example30.getName(), json);

        this.updateIndex(example30, json);        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitEncoding(io.apicurio.datamodels.openapi.v3.models.Oas30Encoding)
     */
    @Override
    public void visitEncoding(Oas30Encoding node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        JsonCompat.setPropertyString(json, Constants.PROP_CONTENT_TYPE, node.contentType);
        JsonCompat.setPropertyNull(json, Constants.PROP_HEADERS);
        JsonCompat.setPropertyString(json, Constants.PROP_STYLE, node.style);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_EXPLODE, node.explode);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_ALLOW_RESERVED, node.allowReserved);
        this.writeExtraProperties(json, node);

        Object encoding = JsonCompat.getProperty(parent, Constants.PROP_ENCODING);
        if (encoding == null) {
            encoding = JsonCompat.objectNode();
            JsonCompat.setProperty(parent, Constants.PROP_ENCODING, encoding);
        }
        JsonCompat.setProperty(encoding, node.getName(), json);

        this.updateIndex(node, json);        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitMediaType(io.apicurio.datamodels.openapi.v3.models.Oas30MediaType)
     */
    @Override
    public void visitMediaType(Oas30MediaType node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        JsonCompat.setPropertyNull(json, Constants.PROP_SCHEMA);
        JsonCompat.setProperty(json, Constants.PROP_EXAMPLE, node.example);
        JsonCompat.setPropertyNull(json, Constants.PROP_EXAMPLES);
        JsonCompat.setPropertyNull(json, Constants.PROP_ENCODING);
        this.writeExtraProperties(json, node);

        Object content = JsonCompat.getProperty(parent, Constants.PROP_CONTENT);
        if (content == null) {
            content = JsonCompat.objectNode();
            JsonCompat.setProperty(parent, Constants.PROP_CONTENT, content);
        }
        JsonCompat.setProperty(content, node.getName(), json);

        this.updateIndex(node, json);        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitHeaderDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30HeaderDefinition)
     */
    @Override
    public void visitHeaderDefinition(Oas30HeaderDefinition node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        this.writeHeader(json, node);
        this.writeExtraProperties(json, node);

        Object headers = JsonCompat.getProperty(parent, Constants.PROP_HEADERS);
        if (headers == null) {
            headers = JsonCompat.objectNode();
            JsonCompat.setProperty(parent, Constants.PROP_HEADERS, headers);
        }
        JsonCompat.setProperty(headers, node.getName(), json);

        this.updateIndex(node, json);        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitRequestBody(io.apicurio.datamodels.openapi.v3.models.Oas30RequestBody)
     */
    @Override
    public void visitRequestBody(Oas30RequestBody node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        this.writeRequestBody(json, node);
        this.writeExtraProperties(json, node);

        JsonCompat.setProperty(parent, Constants.PROP_REQUEST_BODY, json);

        this.updateIndex(node, json);        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitRequestBodyDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30RequestBodyDefinition)
     */
    @Override
    public void visitRequestBodyDefinition(Oas30RequestBodyDefinition node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        this.writeRequestBody(json, node);
        this.writeExtraProperties(json, node);

        Object requestBodies = JsonCompat.getProperty(parent, Constants.PROP_REQUEST_BODIES);
        if (requestBodies == null) {
            requestBodies = JsonCompat.objectNode();
            JsonCompat.setProperty(parent, Constants.PROP_REQUEST_BODIES, requestBodies);
        }
        JsonCompat.setProperty(requestBodies, node.getName(), json);

        this.updateIndex(node, json);        
    }
    protected void writeRequestBody(Object json, Oas30RequestBody node) {
        JsonCompat.setPropertyString(json, Constants.PROP_$REF, node.$ref);
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, node.description);
        JsonCompat.setPropertyNull(json, Constants.PROP_CONTENT);
        JsonCompat.setPropertyBoolean(json, Constants.PROP_REQUIRED, node.required);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitExampleDefinition(io.apicurio.datamodels.openapi.v3.models.Oas30ExampleDefinition)
     */
    @Override
    public void visitExampleDefinition(Oas30ExampleDefinition node) {
        this.visitExample(node);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitDiscriminator(io.apicurio.datamodels.openapi.v3.models.Oas30Discriminator)
     */
    @Override
    public void visitDiscriminator(Oas30Discriminator node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        JsonCompat.setPropertyString(json, Constants.PROP_PROPERTY_NAME, node.propertyName);
        JsonCompat.setProperty(json, Constants.PROP_MAPPING, node.mapping);
        this.writeExtraProperties(json, node);

        JsonCompat.setProperty(parent, Constants.PROP_DISCRIMINATOR, json);

        this.updateIndex(node, json);        
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitNotSchema(io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30NotSchema)
     */
    @Override
    public void visitNotSchema(Oas30NotSchema node) {
        this.doVisitSchema(node, Constants.PROP_NOT, false);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitOneOfSchema(io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30OneOfSchema)
     */
    @Override
    public void visitOneOfSchema(Oas30OneOfSchema node) {
        this.doVisitSchema(node, Constants.PROP_ONE_OF, true);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitAnyOfSchema(io.apicurio.datamodels.openapi.v3.models.Oas30Schema.Oas30AnyOfSchema)
     */
    @Override
    public void visitAnyOfSchema(Oas30AnyOfSchema node) {
        this.doVisitSchema(node, Constants.PROP_ANY_OF, true);
    }
    
    /**
     * @see io.apicurio.datamodels.core.io.DataModelWriter#addSchemaDefinitionToParent(java.lang.Object, java.lang.Object, io.apicurio.datamodels.core.models.common.IDefinition)
     */
    @Override
    protected void addSchemaDefinitionToParent(Object parent, Object json, IDefinition node) {
        Object schemas = JsonCompat.getProperty(parent, Constants.PROP_SCHEMAS);
        if (schemas == null) {
            schemas = JsonCompat.objectNode();
            JsonCompat.setProperty(parent, Constants.PROP_SCHEMAS, schemas);
        }
        
        JsonCompat.setProperty(schemas, node.getName(), json);
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.io.OasDataModelWriter#addResponseDefinitionToParent(java.lang.Object, java.lang.Object, io.apicurio.datamodels.core.models.common.IDefinition)
     */
    @Override
    protected void addResponseDefinitionToParent(Object parent, Object json, IDefinition node) {
        Object responses = JsonCompat.getProperty(parent, Constants.PROP_RESPONSES);
        if (responses == null) {
            responses = JsonCompat.objectNode();
            JsonCompat.setProperty(parent, Constants.PROP_RESPONSES, responses);
        }
        
        JsonCompat.setProperty(responses, node.getName(), json);
    }
    
    /**
     * @see io.apicurio.datamodels.core.io.DataModelWriter#addSecuritySchemeToParent(java.lang.Object, java.lang.Object, io.apicurio.datamodels.core.models.common.SecurityScheme)
     */
    @Override
    protected void addSecuritySchemeToParent(Object parent, Object json, SecurityScheme node) {
        Object securitySchemes = JsonCompat.getProperty(parent, Constants.PROP_SECURITY_SCHEMES);
        if (securitySchemes == null) {
            securitySchemes = JsonCompat.objectNode();
            JsonCompat.setProperty(parent, Constants.PROP_SECURITY_SCHEMES, securitySchemes);
        }
        
        JsonCompat.setProperty(securitySchemes, node.getSchemeName(), json);
    }

    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitServer(io.apicurio.datamodels.core.models.common.Server)
     */
    @Override
    public void visitServer(Server node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        writeServer(json, node);
        writeExtraProperties(json, node);

        JsonCompat.appendToArrayProperty(parent, Constants.PROP_SERVERS, json);
        
        this.updateIndex(node, json);
    }
    protected void writeServer(Object json, Server node) {
        JsonCompat.setPropertyString(json, Constants.PROP_URL, node.url);
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, node.description);
        JsonCompat.setPropertyNull(json, Constants.PROP_VARIABLES);
    }
    
    /**
     * @see io.apicurio.datamodels.openapi.v3.visitors.IOas30Visitor#visitServerVariable(io.apicurio.datamodels.core.models.common.ServerVariable)
     */
    @Override
    public void visitServerVariable(ServerVariable node) {
        Object parent = this.lookupParentJson(node);
        Object json = JsonCompat.objectNode();
        writeServerVariable(json, node);
        writeExtraProperties(json, node);
        
        // Set the variable as a property on the parent's "variables" child object
        Object variables = JsonCompat.getProperty(parent, Constants.PROP_VARIABLES);
        if (variables == null) {
            variables = JsonCompat.objectNode();
            JsonCompat.setProperty(parent, Constants.PROP_VARIABLES, variables);
        }
        JsonCompat.setProperty(variables, node.getName(), json);

        this.updateIndex(node, json);
    }
    protected void writeServerVariable(Object json, ServerVariable node) {
        JsonCompat.setPropertyStringArray(json, Constants.PROP_ENUM, node.enum_);
        JsonCompat.setPropertyString(json, Constants.PROP_DEFAULT, node.default_);
        JsonCompat.setPropertyString(json, Constants.PROP_DESCRIPTION, node.description);
        JsonCompat.setPropertyNull(json, Constants.PROP_VARIABLES);
    }

}
