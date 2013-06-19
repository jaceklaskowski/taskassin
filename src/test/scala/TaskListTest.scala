package pl.japila.taskassin

import com.github.nscala_time.time.Imports._

import org.scalatest.{Spec, BeforeAndAfter}
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

import pl.japila.taskassin.TaskUtils.collectTasks

@RunWith(classOf[JUnitRunner])
class TaskTest extends Spec with ShouldMatchers {

  trait Fixture {
    val now = DateTime.now
    val t1 = new Task("t1", now, 10)
    val t2 = new Task("t2", now + 2.hours)
    val t3 = new Task("t3", now - 2.hours)

    def compByDueDate(t1: Task, t2: Task) = t1.due < t2.due
    val ts = List(t1, t2, t3) sortWith compByDueDate
  }

  describe("List of three tasks (with 2 hours in between)") {
    it("should pop tasks by due order") {
      new Fixture {
        assert(ts.head === t3)
      }
    }
    it("should return the next task for today") {
      new Fixture {
        val hoursPerDay = 6
        val todayTs = collectTasks(ts, hoursPerDay)
        assert(todayTs === List(t3))
      }
    }
  }
}
