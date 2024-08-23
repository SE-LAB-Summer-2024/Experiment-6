package MiniJava.codeGenerator;

public class MemoryFacade {
    private Memory mem;

    public MemoryFacade(Memory memory) {
        mem = memory;
    }

    public int getDateAddress() {
        mem.addDateSize();
        return mem.getDateAddress();
    }
}
