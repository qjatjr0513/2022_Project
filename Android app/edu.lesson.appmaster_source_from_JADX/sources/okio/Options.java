package okio;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class Options extends AbstractList<ByteString> implements RandomAccess {
    final ByteString[] byteStrings;
    final int[] trie;

    private Options(ByteString[] byteStrings2, int[] trie2) {
        this.byteStrings = byteStrings2;
        this.trie = trie2;
    }

    /* renamed from: of */
    public static Options m357of(ByteString... byteStrings2) {
        if (byteStrings2.length == 0) {
            return new Options(new ByteString[0], new int[]{0, -1});
        }
        ArrayList arrayList = new ArrayList(Arrays.asList(byteStrings2));
        Collections.sort(arrayList);
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList2.add(-1);
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            arrayList2.set(Collections.binarySearch(arrayList, byteStrings2[i2]), Integer.valueOf(i2));
        }
        if (((ByteString) arrayList.get(0)).size() != 0) {
            for (int a = 0; a < arrayList.size(); a++) {
                ByteString prefix = (ByteString) arrayList.get(a);
                int b = a + 1;
                while (b < arrayList.size()) {
                    ByteString byteString = (ByteString) arrayList.get(b);
                    if (!byteString.startsWith(prefix)) {
                        continue;
                        break;
                    } else if (byteString.size() == prefix.size()) {
                        throw new IllegalArgumentException("duplicate option: " + byteString);
                    } else if (((Integer) arrayList2.get(b)).intValue() > ((Integer) arrayList2.get(a)).intValue()) {
                        arrayList.remove(b);
                        arrayList2.remove(b);
                    } else {
                        b++;
                    }
                }
            }
            Buffer trieBytes = new Buffer();
            buildTrieRecursive(0, trieBytes, 0, arrayList, 0, arrayList.size(), arrayList2);
            int[] trie2 = new int[intCount(trieBytes)];
            for (int i3 = 0; i3 < trie2.length; i3++) {
                trie2[i3] = trieBytes.readInt();
            }
            if (trieBytes.exhausted() != 0) {
                return new Options((ByteString[]) byteStrings2.clone(), trie2);
            }
            throw new AssertionError();
        }
        throw new IllegalArgumentException("the empty byte string is not a supported option");
    }

    private static void buildTrieRecursive(long nodeOffset, Buffer node, int byteStringOffset, List<ByteString> byteStrings2, int fromIndex, int toIndex, List<Integer> indexes) {
        ByteString from;
        int fromIndex2;
        int prefixIndex;
        int rangeEnd;
        Buffer childNodes;
        int selectChoiceCount;
        int rangeEnd2;
        int prefixIndex2;
        Buffer buffer = node;
        int i = byteStringOffset;
        List<ByteString> list = byteStrings2;
        int fromIndex3 = fromIndex;
        int i2 = toIndex;
        List<Integer> list2 = indexes;
        if (fromIndex3 < i2) {
            int i3 = fromIndex;
            while (i3 < i2) {
                if (list.get(i3).size() >= i) {
                    i3++;
                } else {
                    throw new AssertionError();
                }
            }
            ByteString from2 = byteStrings2.get(fromIndex);
            ByteString to = list.get(i2 - 1);
            if (i == from2.size()) {
                int prefixIndex3 = list2.get(fromIndex3).intValue();
                int fromIndex4 = fromIndex3 + 1;
                fromIndex2 = fromIndex4;
                from = list.get(fromIndex4);
                prefixIndex = prefixIndex3;
            } else {
                fromIndex2 = fromIndex3;
                from = from2;
                prefixIndex = -1;
            }
            if (from.getByte(i) != to.getByte(i)) {
                int selectChoiceCount2 = 1;
                for (int i4 = fromIndex2 + 1; i4 < i2; i4++) {
                    if (list.get(i4 - 1).getByte(i) != list.get(i4).getByte(i)) {
                        selectChoiceCount2++;
                    }
                }
                long childNodesOffset = nodeOffset + ((long) intCount(node)) + 2 + ((long) (selectChoiceCount2 * 2));
                buffer.writeInt(selectChoiceCount2);
                buffer.writeInt(prefixIndex);
                for (int i5 = fromIndex2; i5 < i2; i5++) {
                    byte rangeByte = list.get(i5).getByte(i);
                    if (i5 == fromIndex2 || rangeByte != list.get(i5 - 1).getByte(i)) {
                        buffer.writeInt((int) rangeByte & 255);
                    }
                }
                Buffer childNodes2 = new Buffer();
                int rangeStart = fromIndex2;
                while (rangeStart < i2) {
                    byte rangeByte2 = list.get(rangeStart).getByte(i);
                    int rangeEnd3 = toIndex;
                    int i6 = rangeStart + 1;
                    while (true) {
                        if (i6 >= i2) {
                            rangeEnd = rangeEnd3;
                            break;
                        } else if (rangeByte2 != list.get(i6).getByte(i)) {
                            rangeEnd = i6;
                            break;
                        } else {
                            i6++;
                        }
                    }
                    if (rangeStart + 1 == rangeEnd && i + 1 == list.get(rangeStart).size()) {
                        buffer.writeInt(list2.get(rangeStart).intValue());
                        rangeEnd2 = rangeEnd;
                        byte b = rangeByte2;
                        int i7 = rangeStart;
                        childNodes = childNodes2;
                        selectChoiceCount = selectChoiceCount2;
                        prefixIndex2 = prefixIndex;
                    } else {
                        buffer.writeInt((int) ((childNodesOffset + ((long) intCount(childNodes2))) * -1));
                        rangeEnd2 = rangeEnd;
                        byte b2 = rangeByte2;
                        childNodes = childNodes2;
                        selectChoiceCount = selectChoiceCount2;
                        prefixIndex2 = prefixIndex;
                        buildTrieRecursive(childNodesOffset, childNodes2, i + 1, byteStrings2, rangeStart, rangeEnd2, indexes);
                    }
                    rangeStart = rangeEnd2;
                    childNodes2 = childNodes;
                    prefixIndex = prefixIndex2;
                    selectChoiceCount2 = selectChoiceCount;
                    list2 = indexes;
                }
                int i8 = rangeStart;
                Buffer childNodes3 = childNodes2;
                int i9 = selectChoiceCount2;
                buffer.write(childNodes3, childNodes3.size());
                int rangeStart2 = prefixIndex;
                List<Integer> list3 = indexes;
                return;
            }
            int prefixIndex4 = prefixIndex;
            int i10 = byteStringOffset;
            int max = Math.min(from.size(), to.size());
            int scanByteCount = 0;
            while (i10 < max && from.getByte(i10) == to.getByte(i10)) {
                scanByteCount++;
                i10++;
            }
            long childNodesOffset2 = nodeOffset + ((long) intCount(node)) + 2 + ((long) scanByteCount) + 1;
            buffer.writeInt(-scanByteCount);
            buffer.writeInt(prefixIndex4);
            for (int i11 = byteStringOffset; i11 < i + scanByteCount; i11++) {
                buffer.writeInt((int) from.getByte(i11) & 255);
            }
            if (fromIndex2 + 1 != i2) {
                int i12 = prefixIndex4;
                List<Integer> list4 = indexes;
                Buffer childNodes4 = new Buffer();
                buffer.writeInt((int) ((childNodesOffset2 + ((long) intCount(childNodes4))) * -1));
                Buffer childNodes5 = childNodes4;
                int i13 = scanByteCount;
                buildTrieRecursive(childNodesOffset2, childNodes4, i + scanByteCount, byteStrings2, fromIndex2, toIndex, indexes);
                buffer.write(childNodes5, childNodes5.size());
            } else if (i + scanByteCount == list.get(fromIndex2).size()) {
                int i14 = prefixIndex4;
                buffer.writeInt(indexes.get(fromIndex2).intValue());
            } else {
                throw new AssertionError();
            }
        } else {
            throw new AssertionError();
        }
    }

    public ByteString get(int i) {
        return this.byteStrings[i];
    }

    public final int size() {
        return this.byteStrings.length;
    }

    private static int intCount(Buffer trieBytes) {
        return (int) (trieBytes.size() / 4);
    }
}
