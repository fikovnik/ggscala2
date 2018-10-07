/*
 * Copyright 2017 The Climate Corporation
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
 * See the License for the specific language governing permissions and limitations under the License.
 */

package com.climate.ggscala2

import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File
import breeze.numerics.round

private[ggscala2] trait Screen {

  /**
    * Render an image file (png, jpeg) as an HTML string
    *
    * @param imageFile A File that points to a png/jpg file on disk
    */
  def renderHTML(imageFile: File, scale: Double = 0.33): String = {
    import java.util.Base64
    import java.nio.file.{Files, Paths}

    val image: BufferedImage = ImageIO.read(imageFile)
    val nx = round(image.getWidth * scale).toInt
    val ny = round(image.getHeight * scale).toInt

    // read temporary file bytes and delete the file
    val byteArray = Files.readAllBytes(Paths.get("/tmp/ggscala2.png"))
    //new File(tempFileName).delete

    // convert to base64 and embed PNG
    val base64 = Base64.getEncoder.encodeToString(byteArray)
    "<img src=\"data:image/png;base64, " + base64 + "\", length = " + ny + ", width = " + nx + " />"
  }

}
