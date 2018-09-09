package ai.cup

import com.rojoma.json.v3.ast.JValue
import com.rojoma.json.v3.util.JsonUtil

import scala.util.Random

object Main {
	def onTick(parsed : Map[String, JValue]) : (String, String) = {
		val commands = Seq("left", "right", "stop")
		val command = new Random().shuffle(commands).head
		(command, command)
	}

	def init(parsed : Map[String, JValue]): Unit = {}

	def main(args : Array[String]) {
		val configStr = scala.io.StdIn.readLine().trim
		val config = JsonUtil.parseJson[Map[String, JValue]](configStr).right.get

		init(config)

		while (true) {
			val json = scala.io.StdIn.readLine().trim
			val data = JsonUtil.parseJson[Map[String, JValue]](json)

			val (command, debug) = onTick(data.right.get)
			println(JsonUtil.renderJson(Map("command" -> command, "debug" -> debug)))
		}
	}
}
