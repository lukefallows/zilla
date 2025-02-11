/*
 * Copyright 2021-2023 Aklivity Inc.
 *
 * Aklivity licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.aklivity.zilla.runtime.engine.test.internal.validator.config;

import java.util.function.Function;

import io.aklivity.zilla.runtime.engine.config.ConfigBuilder;
import io.aklivity.zilla.runtime.engine.config.ValidatorConfig;

public class TestValidatorConfigBuilder<T> extends ConfigBuilder<T, TestValidatorConfigBuilder<T>>
{
    private final Function<ValidatorConfig, T> mapper;

    TestValidatorConfigBuilder(
        Function<ValidatorConfig, T> mapper)
    {
        this.mapper = mapper;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Class<TestValidatorConfigBuilder<T>> thisType()
    {
        return (Class<TestValidatorConfigBuilder<T>>) getClass();
    }

    @Override
    public T build()
    {
        return mapper.apply(new TestValidatorConfig());
    }
}
