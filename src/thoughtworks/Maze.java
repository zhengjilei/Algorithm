package thoughtworks;

/**
 * Created by ZhengJilei on 2018/7/28.
 */
class Maze {
    boolean[][] pos;  // pos[i][j] = false 表示墙 , pos[i][j] = true 表示通路
    int x, y;  // 渲染网格的高、宽

    private String command;

    public String render() {
        command = command.trim();

        String[] strs = command.split(System.lineSeparator());
        if (strs.length != 2) {
            return "Incorrect command format.";
        }

        String[] firstLine = strs[0].trim().split(" ");
        if (firstLine.length != 2) {
            return "Incorrect command format.";
        }

        int row = 0, col = 0;
        try {
            row = Integer.parseInt(firstLine[0].trim());
            col = Integer.parseInt(firstLine[1].trim());

            if (row <= 0 || col <= 0) {
                return "Number out of range.";
            }
            this.x = 2 * row + 1;
            this.y = 2 * col + 1;
            pos = new boolean[x][y];

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    pos[2 * i + 1][2 * j + 1] = true;
                }
            }

            String secondLine = strs[1].trim();
            String[] connect = secondLine.split(";");

            for (int i = 0; i < connect.length; i++) {
                String[] s = connect[i].trim().split(" ");
                if (s.length != 2) {
                    return "Incorrect command format.";
                }
                String s1 = s[0].trim();
                String s2 = s[1].trim();

                String[] s1Split = s1.split(",");
                String[] s2Split = s2.split(",");

                if (s1Split.length != 2 || s2Split.length != 2) {
                    return "Incorrect command format.";
                }
                int a1 = Integer.parseInt(s1Split[0].trim());
                int b1 = Integer.parseInt(s1Split[1].trim());

                int a2 = Integer.parseInt(s2Split[0].trim());
                int b2 = Integer.parseInt(s2Split[1].trim());

                if (a1 < 0 || a1 >= x || b1 < 0 || b1 >= y || a2 < 0 || a2 >= x || b2 < 0 || b2 >= y) {
                    return "Number out of range.";
                }

                if (a1 == a2 && Math.abs(b1 - b2) == 1) {
                    int t1 = 2 * a1 + 1;
                    int t2 = (2 * b1 + 1 + 2 * b2 + 1) / 2;
                    pos[t1][t2] = true;

                } else if (Math.abs(a1 - a2) == 1 && b1 == b2) {
                    int t1 = (2 * a1 + 1 + 2 * a2 + 1) / 2;
                    int t2 = 2 * b1 + 1;
                    pos[t1][t2] = true;
                } else {
                    return "Maze format error.";
                }

            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    if (pos[i][j] == false) {
                        sb.append("[W]\t");
                    } else {
                        sb.append("[R]\t");
                    }
                }
                sb.append(System.lineSeparator());
            }
            return sb.toString();

        } catch (NumberFormatException e) {
            return "Invalid number format.";
        }
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
