package pl.japila.taskassin

import org.joda.time.DateTime
import annotation.tailrec

case class Task(label: String, due: DateTime, timeSpanInHours: Int = 6)

object TaskUtils {

  def collectTasks(ts: List[Task], hours: Int): List[Task] = {
    @tailrec
    def collectTasks(ts: List[Task], accTs: List[Task], remHours: Int): List[Task] = 
      ts match {
        case t :: tail if (remHours >= 0 && remHours - t.timeSpanInHours >= 0) =>
          collectTasks(tail, t :: accTs, remHours - t.timeSpanInHours)
        case _ => accTs reverse
      }
    collectTasks(ts, Nil, hours)
  }
}
