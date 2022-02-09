


import JavaFactories.{AbstractFactory, DataFrameFactory}
import dataFrame.{ScalaDataFrame, ScalaDirectory, ScalaFileDF, ScalaListFilter}
import org.scalatest.funsuite.AnyFunSuite
import visitor.{CounterVisitor, FilterVisitor}

import java.io.File

class Test extends AnyFunSuite {

  val input = new File("dataFiles/cities.csv")
  val factory: DataFrameFactory = AbstractFactory.create(input)
  val dataFile: ScalaDataFrame = new ScalaFileDF(factory.frame(input))
  val dataFile2: ScalaDataFrame = new ScalaFileDF(factory.frame(input))
  val testDirectory: ScalaDirectory = new ScalaDirectory()

  test("**** TEST 0 - DataFrame ****") {
    println("**** TEST 0 - DataFrame ****")
    println("Size DF: "+dataFile.size)
    println("Size Labels: "+dataFile.columns)
    println("AT: "+dataFile.at(0,"LatD"))
    println("IAT: "+dataFile.iat(0,0))
    println("KeyList"+dataFile.getCategories)
  }

  test("**** Test Directory ****"){
    println("***** TEST 1 - ScalaDirectory *****")
    println("Added a Dataframe of size:"+dataFile.size+testDirectory.add(dataFile))
    println("Added a Dataframe of size:"+dataFile2.size+testDirectory.add(dataFile))
    println("Added a Dataframe of size:"+dataFile2.size+testDirectory.add(dataFile))
    println("Added a Dataframe of size:"+dataFile2.size+testDirectory.add(dataFile))
    println(testDirectory)
    println("DIRECTORY-SIZE: " + testDirectory.size())
    println("LABELS-SIZE: " + testDirectory.columns())
    println("AT: "+testDirectory.at(127, "LatD"))
    println("IAT: "+testDirectory.iat(127, 1))
  }

  test("**** Test Visitors ****"){
    println("***** TEST 2 - Visitors *****")
    testDirectory.add(dataFile)
    testDirectory.add(dataFile2)
    testDirectory.add(dataFile2)
    testDirectory.add(dataFile2)

    val c = new CounterVisitor()
    val f = new FilterVisitor(x => x.asInstanceOf[Long]>48,"LatD")
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

  test("**** Test Recursivity ****"){
    println("***** TEST 3 - Recursivity *****")
    val dataframeTest: ScalaFileDF = new ScalaFileDF(factory.frame(input))
    val listTestTail = List[Double](1.0, 999.9,55.8,222.2)
    val listaTestStack = dataframeTest.getField("State").map(_.toString)
    println(ScalaListFilter.roundOver100(listTestTail))
    println(ScalaListFilter.replaceWord(listaTestStack,"OH", "NULL"))


    val modifyLatD = ScalaListFilter.curry(dataframeTest.getField("LatD").map(_.toString.toInt)) _
    val add50 = ScalaListFilter.curry (_:List[Int]) (x=>x+50)

    println(ScalaListFilter.curry(dataframeTest.getField("LatD").map(_.toString.toInt))(x=>x+50)) //Both parameters
    println(modifyLatD(x=>x+50))
    println(add50(dataframeTest.getField("LatD").map(_.toString.toInt)))
  }
}
