/***********************************************************************
* Copyright (c) 2013-2016 Commonwealth Computer Research, Inc.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Apache License, Version 2.0
* which accompanies this distribution and is available at
* http://www.opensource.org/licenses/apache2.0.php.
*************************************************************************/

package org.locationtech.geomesa.utils

import org.geotools.data.FeatureReader
import org.geotools.data.collection.DelegateFeatureReader
import org.geotools.feature.collection.DelegateFeatureIterator
import org.geotools.geometry.jts.ReferencedEnvelope
import org.geotools.referencing.CRS
import org.geotools.referencing.crs.DefaultGeographicCRS
import org.joda.time.format.DateTimeFormat
import org.opengis.feature.simple.{SimpleFeature, SimpleFeatureType}

import scala.util.Try

package object geotools {
  // use the epsg jar if it's available (e.g. in geoserver), otherwise use the less-rich constant
  val CRS_EPSG_4326 = try { CRS.decode("EPSG:4326") } catch { case t: Throwable => DefaultGeographicCRS.WGS84 }
  // we make this a function, as envelopes are mutable
  def wholeWorldEnvelope = new ReferencedEnvelope(-180, 180, -90, 90, CRS_EPSG_4326)
  // date format with geotools pattern
  val GeoToolsDateFormat = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZoneUTC()

  type FR = FeatureReader[SimpleFeatureType, SimpleFeature]
  type DFR = DelegateFeatureReader[SimpleFeatureType, SimpleFeature]
  type DFI = DelegateFeatureIterator[SimpleFeature]
}
