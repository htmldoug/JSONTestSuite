import java.io.IOException
import java.nio.file.{Files, Paths}

import ujson._

object TestJSONParsing extends App {

  if (args.length == 0) {
    println("Usage: java TestJSONParsing file.json")
    sys.exit(2)
  }

  val bytes: Array[Byte] = try {
    Files.readAllBytes(Paths.get(args(0)))
  } catch {
    case e: IOException =>
      println("not found")
      sys.exit(2)
  }

  try {
    val visitor: Visitor[_, Any] = sys.props.get("visitor") match {
      case Some("bytes") => BytesRenderer()
      case Some("string") => StringRenderer()
      case Some("js") => Js
      case _ => NoOpVisitor
    }

    transform(bytes, visitor)
    println("valid")
  } catch {
    case p: ParsingFailedException =>
      println("invalid")
      sys.exit(1)
    case t: Throwable =>
      t.printStackTrace()
      sys.exit(2)
  }
}
