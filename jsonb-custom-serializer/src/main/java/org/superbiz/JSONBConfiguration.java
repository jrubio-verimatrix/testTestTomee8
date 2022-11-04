/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.superbiz;

import java.lang.reflect.Type;
import java.util.Scanner;
import java.lang.annotation.Annotation;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import javax.ws.rs.core.MediaType;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.MultivaluedMap;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.WebApplicationException;


@Provider
public class JSONBConfiguration implements MessageBodyWriter<Object>, MessageBodyReader<Object> {

    private static final Jsonb jsonb = JsonbBuilder.create();

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public Object readFrom(Class<Object> clazz, Type genericType, Annotation[] annotations,
                           MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        String stringResult = convertStreamToString(entityStream);
        return jsonb.fromJson(stringResult, genericType);
    }

    @SuppressWarnings("resource")
    private static String convertStreamToString(java.io.InputStream is) {
        try (Scanner s = new Scanner(is).useDelimiter("\\A")) {
            return s.hasNext() ? s.next() : "";
        }
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public void writeTo(Object obj, Class<?> type, Type genericType, Annotation[] annotations,
                        MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        String strData = jsonb.toJson(obj);
        jsonb.toJson(obj, entityStream);
    }
}

