package pl.japila.taskassin

import com.github.nscala_time.time.Imports._

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class TaskTest extends Spec with ShouldMatchers {

  describe("Task list") {
    it("should return the most current task") {
      val t1 = new Task("t1", DateTime.now)
      val t2 = new Task("t2", DateTime.now + 2.minutes)

      /* http://mkaz.com/solog/how-to-sort-collections-in-scala */
      /* sort by date */
      def compByTime(t1: Task, t2: Task) = t1.time < t2.time

      val ts = List(t2, t1) sortWith compByTime
      ts.head should equal(t1)
    }
  }
}
