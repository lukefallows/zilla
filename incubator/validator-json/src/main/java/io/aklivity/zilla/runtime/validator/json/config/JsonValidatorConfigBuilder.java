/*
 * Copyright 2021-2023 Aklivity Inc
 *
 * Licensed under the Aklivity Community License (the "License"); you may not use
 * this file except in compliance with the License.  You may obtain a copy of the
 * License at
 *
 *   https://www.aklivity.io/aklivity-community-license/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */
package io.aklivity.zilla.runtime.validator.json.config;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import io.aklivity.zilla.runtime.engine.config.CatalogedConfig;
import io.aklivity.zilla.runtime.engine.config.CatalogedConfigBuilder;
import io.aklivity.zilla.runtime.engine.config.ConfigBuilder;

public class JsonValidatorConfigBuilder<T> extends ConfigBuilder<T, JsonValidatorConfigBuilder<T>>
{
    private final Function<JsonValidatorConfig, T> mapper;

    private List<CatalogedConfig> catalogs;

    JsonValidatorConfigBuilder(
        Function<JsonValidatorConfig, T> mapper)
    {
        this.mapper = mapper;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Class<JsonValidatorConfigBuilder<T>> thisType()
    {
        return (Class<JsonValidatorConfigBuilder<T>>) getClass();
    }

    public CatalogedConfigBuilder<JsonValidatorConfigBuilder<T>> catalog()
    {
        return CatalogedConfig.builder(this::catalog);
    }

    public JsonValidatorConfigBuilder<T> catalog(
        CatalogedConfig catalog)
    {
        if (catalogs == null)
        {
            catalogs = new LinkedList<>();
        }
        catalogs.add(catalog);
        return this;
    }

    @Override
    public T build()
    {
        return mapper.apply(new JsonValidatorConfig(catalogs));
    }
}
