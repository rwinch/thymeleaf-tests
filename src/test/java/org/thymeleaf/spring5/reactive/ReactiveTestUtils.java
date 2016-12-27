/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2016, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.thymeleaf.spring5.reactive;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.thymeleaf.exceptions.TemplateProcessingException;
import org.thymeleaf.util.ClassLoaderUtils;

public final class ReactiveTestUtils {


    public static final String TEMPLATE_PATH_BASE = "spring5/reactive/";




    public static String bufferAsString(final DataBuffer dataBuffer, final Charset charset) {
        try {
            return IOUtils.toString(dataBuffer.asInputStream(), charset.name());
        } catch (final IOException e) {
            throw new TemplateProcessingException("Error converting databuffer to string", e);
        }
    }




    public static String readExpectedResults(final String templateName, final Charset charset) {
        final String path = TEMPLATE_PATH_BASE + templateName + "-result.html";
        try {
            final InputStream templateIS = ClassLoaderUtils.loadResourceAsStream(path);
            return IOUtils.toString(templateIS, charset.name());
        } catch (final IOException e) {
            throw new TemplateProcessingException("Could not read '" + path + "'", e);
        }
    }




    public static String readExpectedNormalizedResults(final String templateName, final Charset charset) {
        return normalizeResult(readExpectedResults(templateName, charset));
    }




    public static String normalizeResult(final String result) {
        final StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < result.length(); i++) {
            final char c = result.charAt(i);
            if (!Character.isWhitespace(c)) {
                strBuilder.append(c);
            }
        }
        return strBuilder.toString();
    }




    public static List<String> normalizeResults(final List<String> results) {
        final List<String> normalizedResults = new ArrayList<>();
        for (final String result : results) {
            normalizedResults.add(normalizeResult(result));
        }
        return normalizedResults;
    }




    private ReactiveTestUtils() {
        super();
    }

    
}