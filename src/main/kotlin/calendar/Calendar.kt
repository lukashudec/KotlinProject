package calendar

import java.util.*



class Calendar(_rawCalendar: MutableList<Array<String>>, _bounds: Array<String>) {
    private var rawCalendar: MutableList<Array<String>> = _rawCalendar
    private var bounds: Array<String> = _bounds
    private var calendar: List<Array<Int>> = formatInput()

    constructor(rawCalendar: String, bounds: String) : this(getTime(rawCalendar), getTime(bounds)[0])

    companion object {
    fun getTime(stringTime: String): MutableList<Array<String>> {
        val result : MutableList<Array<String>> = mutableListOf()
        for (time_frame in stringTime.split(",").toTypedArray()) {
            result.add(time_frame.split("-").toTypedArray())
        }
        return result
    } }

    private fun formatTime(time: Int): String {
        val timeString = time.toString()
        return if (timeString.length == 1) "0$timeString" else timeString
    }

    private fun comp(a: String, b: String?, i: Int): String {
        return if (a.compareTo(b!!) == i) a else b
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val calendar2: Calendar = other as Calendar
        return this.hashCode() == calendar2.hashCode()
    }

    override fun hashCode(): Int {
        return Objects.hash(this.toString())
    }

    override fun toString(): String {
        return rawCalendar.toTypedArray().contentDeepToString() + " / " + bounds.contentToString()
    }

    fun getCalendar(): String {
        return calendar.toTypedArray().contentDeepToString()
    }

    fun mergeWithCalendar(cal2: Calendar) : Calendar{
        val newCalendar: MutableList<Array<String>> = rawCalendar
        newCalendar.addAll(cal2.rawCalendar)
        val newBounds = arrayOf(comp(bounds[0], cal2.bounds[0], 1), comp(bounds[1], cal2.bounds[1], -1))
        newCalendar.sortBy{ o: Array<String> -> o[0] }
        return Calendar(newCalendar, newBounds)
    }

    private fun formatInput(): List<Array<Int>> {
        val calendar: MutableList<Array<String>> = ArrayList()
        val result: MutableList<Array<Int>> = ArrayList()
        calendar.add(arrayOf("00:00", bounds[0]))
        calendar.addAll(rawCalendar)
        calendar.add(arrayOf(bounds[1], "00:00"))
        for (s in calendar) {
            val timeFrom = s[0].split(":").toTypedArray()
            val timeTo = s[1].split(":").toTypedArray()
            result.add(arrayOf(timeFrom[0].toInt() * 60 + timeFrom[1].toInt(), timeTo[0].toInt() * 60 + timeTo[1].toInt()))
        }
        return result
    }

    fun getFreeTime(): List<Array<Int>> {
        return getFreeTime(30)
    }

    private fun getFreeTime(minDuration: Int): List<Array<Int>> {
        val result: MutableList<Array<Int>> = ArrayList()
        for (i in 0 until calendar.size - 1) {
            val event = calendar[i]
            val nextEvent = calendar[i + 1]
            if (nextEvent[0] - event[1] >= minDuration) {
                result.add(arrayOf(event[1], nextEvent[0]))
            }
        }
        return result
    }

    fun getPossibleEvents(cal2: Calendar, minDuration: Int): List<Array<Int>> {
        return mergeWithCalendar(cal2).getFreeTime(minDuration)
    }

    fun getPrettyTime(): String {
        return getPrettyTime(30)
    }

    fun getPrettyTime(minDuration: Int): String {
        val result: MutableList<Array<String>> = ArrayList()
        for (i in getFreeTime(minDuration)) {
            result.add(
                arrayOf(
                    formatTime(i[0] / 60) + ':' + formatTime(i[0] % 60),
                    formatTime(i[1] / 60) + ':' + formatTime(i[1] % 60)
                )
            )
        }
        return result.toTypedArray().contentDeepToString()
    }
}