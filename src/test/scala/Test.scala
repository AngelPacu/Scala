

import JavaFactories.AbstractFactory
import dataFrame.{ScalaDataFrame, ScalaDirectory, ScalaFileDF}
import java.io.File


object Test {

  def main(args: Array[String]): Unit = {
    //var input = List[File]("dataFiles/cities.csv","dataFiles/cities.csv")
    val input = new File("dataFiles/cities.csv")
    val factory = AbstractFactory.create(input)
    val dataFile: ScalaDataFrame = new ScalaFileDF(factory.frame(input))
    val dataFile2: ScalaDataFrame = new ScalaFileDF(factory.frame(input))
    println(dataFile.size())
    println(dataFile.columns())
    println(dataFile.iat(0,0))
    val directoriProva: ScalaDirectory = new ScalaDirectory()
    directoriProva.add(dataFile)
    directoriProva.add(dataFile2)
    directoriProva.add(dataFile2)
    directoriProva.add(dataFile2)
    println("Tamaño Directorio"+directoriProva.size())
    println(directoriProva.columns())
    println(directoriProva.at(511,"LatD"))
  }
}
