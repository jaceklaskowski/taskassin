package pl.japila.taskassin

import org.joda.time.DateTime
import annotation.tailrec

case class Task(label: String, due: DateTime, timeUnits: Int = 6,
  recurring: Boolean = false, recurTimes: Int = 0, intervalUnits: Int = 0)

object TaskUtils {

  def collectTasks(ts: List[Task], hours: Int): List[Task] = {
    @tailrec
    def collectTasks(ts: List[Task], accTs: List[Task], remHours: Int): List[Task] = {
      ts match {
        case t :: tail if (remHours >= 0 && remHours - t.timeUnits >= 0) =>
          if (t.recurring) {
            val tt = t.copy(recurTimes = t.recurTimes - 1)
            val tss = tt :: tail
            collectTasks(tss, t :: accTs, remHours - t.timeUnits - t.intervalUnits)
          } else
            collectTasks(tail, t :: accTs, remHours - t.timeUnits)
        case _ => accTs reverse
      }
    }
    collectTasks(ts, Nil, hours)
  }
}
