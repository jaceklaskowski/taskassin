package pl.japila.taskassin

import com.github.nscala_time.time.Imports._

import org.scalatest.{Spec, BeforeAndAfter}
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class TaskTest extends Spec with ShouldMatchers {

  trait Fixture {
    val t1 = new Task("t1", DateTime.now)
    val t2 = new Task("t2", DateTime.now + 2.minutes)
    def compByDueDate(t1: Task, t2: Task) = t1.due < t2.due
    val ts = List(t2, t1) sortWith compByDueDate
  }

  describe("List of three tasks (with 5 minutes in between)") {
    it("should pop tasks by due order") {
      new Fixture {
        assert(ts.head === t1)
      }
    }
    it("should return the tasks for today") (pending)
  }
}
