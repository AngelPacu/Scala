



import JavaFactories.AbstractFactory
import dataFrame.{ScalaDataFrame, ScalaDirectory, ScalaFileDF}
import org.scalatest.funsuite.AnyFunSuite

import java.io.File
import visitor.{CounterVisitor, FilterVisitor}

class Test extends AnyFunSuite {

  val input = new File("dataFiles/cities.csv")
  val factory = AbstractFactory.create(input)
  val dataFile: ScalaDataFrame = new ScalaFileDF(factory.frame(input))
  val dataFile2: ScalaDataFrame = new ScalaFileDF(factory.frame(input))
  val testDirectory: ScalaDirectory = new ScalaDirectory()

  test("**** TEST 1 - DataFrame ****") {
      println("**** TEST 1 - DataFrame ****")
      println("Size DF: "+dataFile.size())
      println("Size Labels: "+dataFile.columns())
      println("AT: "+dataFile.at(0,"LatD"))
      println("IAT: "+dataFile.iat(0,0))
    }

  test("**** Test Directory ****"){
    println("***** TEST 2 - ScalaDirectory *****")
    testDirectory.add(dataFile)
    testDirectory.add(dataFile2)
    testDirectory.add(dataFile2)
    testDirectory.add(dataFile2)
    println("Size DIRECTORY: " + testDirectory.size())
    println("AT: "+testDirectory.columns())
    println("AT: "+testDirectory.at(127, "LatD"))
    println("IAT: "+testDirectory.iat(127, 1))
  }

  test("**** Test Visitors ****"){
    println("***** TEST 3 - Visitors *****")
    testDirectory.add(dataFile)
    testDirectory.add(dataFile2)
    testDirectory.add(dataFile2)
    testDirectory.add(dataFile2)

    val c = new CounterVisitor()
    val f = new FilterVisitor((x) => x.asInstanceOf[Long]>48,"LatD")
    testDirectory.accept(c)
    println("DataFrame files: " + c.files + " DataFrame dirs: " + c.dirs)

    val root: ScalaDirectory = new ScalaDirectory()
    testDirectory.remove(dataFile)
    root.add(testDirectory)
    root.add(dataFile)
    root.accept(c)
    root.accept(c)
    root.accept(f)
    println("DataFrame files: " + c.files + " DataFrame dirs: " + c.dirs)
    println("Query:"+f.listResult)
    testDirectory.accept(f)
    println("Query:"+f.listResult)
  }


}
