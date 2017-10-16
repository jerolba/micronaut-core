/*
 * Copyright 2017 original authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package org.particleframework.core.value;

import org.particleframework.core.convert.TypeConverter;
import org.particleframework.core.type.Argument;

import java.util.Map;
import java.util.Optional;

/**
 * An interface for any type that is able to resolve and convert values
 *
 * @see TypeConverter
 * @author Graeme Rocher
 * @since 1.0
 */
public interface ValueResolver {

    /**
     * Resolve the given property for the given name
     *
     * @param name The name
     * @param requiredType The required type
     * @param <T> The concrete type
     * @return An optional containing the property value if it exists and is able to be converted
     */
    <T> Optional<T> get(CharSequence name, Class<T> requiredType);

    /**
     * Resolve the given property for the given name
     *
     * @param name The name
     * @param requiredType The required type
     * @param <T> The concrete type
     * @return An optional containing the property value if it exists and is able to be converted
     */
    default <T> Optional<T> get(CharSequence name, Argument<T> requiredType) {
        return get(name, requiredType.getType());
    }
    /**
     * Resolve the given property for the given name
     *
     * @param name The name
     * @param requiredType The required type
     * @param defaultValue The default value
     * @param <T> The concrete type
     * @return An optional containing the property value if it exists
     */
    default <T> T get(CharSequence name, Class<T> requiredType, T defaultValue) {
        return get(name, requiredType).orElse(defaultValue);
    }

    /**
     * Create a new {@link ValueResolver} for the given map
     *
     * @param map The map
     * @return The {@link ValueResolver}
     */
    static ValueResolver of(Map<CharSequence, ?> map) {
        return new MapValueResolver(map);
    }
}