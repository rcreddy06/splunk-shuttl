// Copyright (C) 2011 Splunk Inc.
//
// Splunk Inc. licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.splunk.shuttl.archiver.filesystem;

import java.io.File;

import com.splunk.shuttl.archiver.ConfigurationPaths;

/**
 * Gets properties for back-end implementations of {@link ArchiveFileSystem} for
 * their custom needs.
 */
public class BackendConfigurationFiles {

	private final File configurationDir;

	/**
	 * @param configurationDir
	 *          directory where configuration files live.
	 */
	public BackendConfigurationFiles(File configurationDir) {
		this.configurationDir = configurationDir;
	}

	/**
	 * @return configuration file by name.
	 */
	public File getByName(String name) {
		File file = new File(configurationDir, name);
		if (!file.exists())
			throw new ConfigurationFileDoesNotExist(file);
		return file;
	}

	public static BackendConfigurationFiles create() {
		return new BackendConfigurationFiles(
				ConfigurationPaths.getBackendConfigDirectory());
	}

	public static class ConfigurationFileDoesNotExist extends RuntimeException {

		private static final long serialVersionUID = 1L;

		public ConfigurationFileDoesNotExist(File file) {
			super(file.getAbsolutePath());
		}

	}
}
