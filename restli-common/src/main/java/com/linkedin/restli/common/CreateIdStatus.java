/*
   Copyright (c) 2014 LinkedIn Corp.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package com.linkedin.restli.common;


import com.linkedin.data.DataMap;
import com.linkedin.restli.internal.common.URIParamUtils;


/**
 * CreateStatus that keeps track of a strongly typed version of the returned key.
 *
 * @author Moira Tagle
 */
public class CreateIdStatus<K> extends CreateStatus
{
  private final K _key;

  /**
   * The id field of the dataMap should match the given key.
   * This method is for internal use only.  Others should use {@link com.linkedin.restli.common.CreateIdStatus#CreateIdStatus(int, Object, ErrorResponse, ProtocolVersion)}.
   *
   * @see {@link com.linkedin.restli.internal.common.CreateIdStatusDecoder}
   * @param dataMap the underlying DataMap of the CreateIdStatus response. This Data should fit the {@link com.linkedin.restli.common.CreateStatus} schema.
   * @param key The strongly typed key.  Can be null.
   */
  public CreateIdStatus(DataMap dataMap, K key)
  {
    super(dataMap);
    _key = key;
  }

  /**
   * @param status the individual http status
   * @param key the key; can be null
   * @param error the {@link ErrorResponse}; can be null
   * @param version the {@link com.linkedin.restli.common.ProtocolVersion}
   */
  public CreateIdStatus(int status, K key, ErrorResponse error, ProtocolVersion version)
  {
    super(createDataMap(status, key, error, version));
    _key = key;
  }

  /**
   * create a DataMap matching the schema of {@link com.linkedin.restli.common.CreateStatus} with the given data
   * @param status the individual http status
   * @param key the key; can be null
   * @param error the {@link ErrorResponse}; can be null
   * @param version the the {@link com.linkedin.restli.common.ProtocolVersion}, used to serialize the key
   * @return a {@link com.linkedin.data.DataMap} containing the given data
   */
  private static DataMap createDataMap(int status, Object key, ErrorResponse error, ProtocolVersion version)
  {
    CreateStatus createStatus = new CreateStatus();
    createStatus.setStatus(status);
    if (key != null)
    {
      @SuppressWarnings("deprecation")
      CreateStatus c = createStatus.setId(URIParamUtils.encodeKeyForBody(key, false, version));
    }
    if (error != null)
    {
      createStatus.setError(error);
    }
    return createStatus.data();
  }

  /**
   * @return the strongly typed key associated with this create.
   */
  public K getKey()
  {
    return _key;
  }
}