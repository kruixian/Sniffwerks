package eu.faircode.netguard;

/**
 * Created by kruix on 12/7/2017.
 */

public class TimeInterval {
    private int id;
    private long start;
    private long end;
    private int count;

    public TimeInterval(int id, long start, long end)
    {
        this.id = id;
        this.start = start;
        this.end = end;
    }

    public void incrementCount()
    {
        this.count = this.count + 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
