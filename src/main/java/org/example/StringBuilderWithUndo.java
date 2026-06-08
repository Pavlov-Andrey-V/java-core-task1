package org.example;

import java.util.Stack;

public class StringBuilderWithUndo {
    private StringBuilder stringBuilder;
    private Stack<Snapshot> snapshots;

    public StringBuilderWithUndo() {
        stringBuilder = new StringBuilder();
        snapshots = new Stack<>();
    }

    private static class Snapshot {
        private final String conditional;

        public Snapshot(String conditional) {
            this.conditional = conditional;
        }

        public String getConditional() {
            return conditional;
        }
    }

    private void takeSnapshot() {
        snapshots.push(new Snapshot(stringBuilder.toString()));
    }

    public StringBuilderWithUndo append(String someString) {
        takeSnapshot();
        stringBuilder.append(someString);
        return this;
    }

    public void undo() {
        if (!snapshots.isEmpty()) {
            Snapshot lastSnapshot = snapshots.pop();
            stringBuilder = new StringBuilder(lastSnapshot.getConditional());
        }
    }
}