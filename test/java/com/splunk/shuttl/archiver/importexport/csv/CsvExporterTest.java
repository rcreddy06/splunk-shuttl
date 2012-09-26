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
package com.splunk.shuttl.archiver.importexport.csv;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

import java.io.File;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.splunk.shuttl.archiver.importexport.BucketFileCreator;
import com.splunk.shuttl.archiver.model.Bucket;

@Test(groups = { "fast-unit" })
public class CsvExporterTest {

	private CsvExporter csvExporter;
	private BucketFileCreator bucketFileCreator;
	private BucketToCsvFileExporter bucketToCsvFileExporter;

	@BeforeMethod
	public void setUp() {
		bucketToCsvFileExporter = mock(BucketToCsvFileExporter.class);
		bucketFileCreator = mock(BucketFileCreator.class);
		csvExporter = new CsvExporter(bucketToCsvFileExporter, bucketFileCreator);
	}

	public void _givenBucket_exportsToACsvFileAndCreatesTheBucketObject() {
		Bucket bucket = mock(Bucket.class);
		Bucket csvBucket = mock(Bucket.class);
		File csvFile = mock(File.class);
		when(bucketToCsvFileExporter.exportBucketToCsv(bucket)).thenReturn(csvFile);
		when(bucketFileCreator.createBucketWithFile(csvFile, bucket)).thenReturn(
				csvBucket);

		Bucket actualBucket = csvExporter.changeFormat(bucket);
		assertEquals(csvBucket, actualBucket);
	}
}
