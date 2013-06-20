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
    val t1 = Task("t1", now, 10)
    val t2 = Task("t2", now + 2.hours)
    val t3 = Task("t3", now - 2.hours)
    val t4r = Task(label = "t4r",
                   due = now - 2.hours, 
                   timeUnits = 1,
                   recurring = true,
                   recurTimes = 2,
                   intervalUnits = 1)

    def compByDueDate(t1: Task, t2: Task) = t1.due < t2.due
    val ts = List(t1, t2, t3, t4r) sortWith compByDueDate
  }

  describe("List of three tasks (with 2 hours in between)") {
    it("should pop tasks by due order") {
      new Fixture {
        assert(ts.head === t3)
      }
    }
    it("should return the tasks for today") {
      new Fixture {
        val hoursPerDay = 6
        val todayTs = collectTasks(ts, hoursPerDay)
        assert(todayTs === List(t3))
      }
    }
  }

  describe("List of 3 tasks (with 1 recurring task)") {
    it("should return recurring task twice") {
      new Fixture {
        val timeUnits = 9
        val todayTs = collectTasks(ts, timeUnits)
        assert(todayTs.size === 3)
        assert(todayTs.head === t3)
        assert(todayTs.tail.head === t4r)
        val t4rCopied = t4r.copy(recurTimes = t4r.recurTimes - 1)
        assert(todayTs.tail.tail.head === t4rCopied)
      }
    }
  }
}
