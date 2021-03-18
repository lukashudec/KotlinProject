package unitTests

import calendar.Calendar
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CalendarTest {
    private var calendar1: Calendar = Calendar(
        listOf(arrayOf("09:00", "10:30"), arrayOf("12:00", "13:00"), arrayOf("16:00", "18:00")) as MutableList<Array<String>>, arrayOf("09:00", "20:00"))
    private var calendar2: Calendar = Calendar("09:00-10:30,12:00-13:00,16:00-18:00", "09:00-20:00")
    private var calendar3: Calendar = Calendar("12:00-13:00,16:00-18:00", "09:00-20:00")
    private var calendar4: Calendar = Calendar("10:00-11:45,12:30-14:30", "10:00-18:30")

    @Test
    fun test_toString() {
        val result = "[[09:00, 10:30], [12:00, 13:00], [16:00, 18:00]] / [09:00, 20:00]"
        assertEquals(result, calendar1.toString(), "Not equal")
    }

    @Test
    fun test_calendarCreation() {
        assertEquals(calendar1.toString(), calendar2.toString(), "Not equal")
    }

    @Test
    fun test_calendarEquality() {
        assertEquals(calendar2, calendar1, "Not equal")
        assertEquals(calendar1.getCalendar(), calendar2.getCalendar(), "Not equal")
    }

    @Test
    fun test_calendarMerge() {
        val result = Calendar("10:00-11:45,12:00-13:00,12:30-14:30,16:00-18:00", "10:00-18:30")
        assertEquals(calendar3.mergeWithCalendar(calendar4).toString(), result.toString(), "Not equal")
    }

    @Test
    fun test_calendarGetPossibleEventsWith() {
        val cal3: List<Array<Int>> = calendar3.mergeWithCalendar(calendar4).getFreeTime()
        val cal4: List<Array<Int>> = calendar3.getPossibleEvents(calendar4, 30)
        Assertions.assertArrayEquals(cal3.toTypedArray(), cal4.toTypedArray(), "Not equal")
    }

    @Test
    fun test_calendarGetFreeTimeWithParam() {
        val cal1 = Calendar(
            "09:00-10:30,11:00-13:00,16:00-18:00",
            "09:00-20:00"
        )
        assertEquals(
            cal1.getPrettyTime(),
            "[[10:30, 11:00], [13:00, 16:00], [18:00, 20:00]]", "Not equal"
        )
        assertEquals(
            cal1.getPrettyTime(30),
            "[[10:30, 11:00], [13:00, 16:00], [18:00, 20:00]]", "Not equal"
        )
        assertEquals(
            cal1.getPrettyTime(60),
            "[[13:00, 16:00], [18:00, 20:00]]", "Not equal"
        )
        assertEquals(
            cal1.getPrettyTime(121),
            "[[13:00, 16:00]]", "Not equal"
        )
        assertEquals(
            cal1.getPrettyTime(181),
            "[]", "Not equal"
        )
    }

}