package oop.class_problems;

public class HostelAllocationService {

    public static class HostelRoom {
        private String roomNo;
        private int beds;
        private int occupied;

        public HostelRoom(String roomNo, int beds, int occupied) {
            this.roomNo = roomNo;
            this.beds = beds;
            this.occupied = occupied;
        }

        public boolean allot(String name) {
            if (occupied < beds) {
                occupied++;
                return true;
            }
            return false;
        }

        public String getRoomNo() {
            return roomNo;
        }

        public int getBeds() {
            return beds;
        }

        public int getOccupied() {
            return occupied;
        }
    }

    public static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        if (rooms == null) {
            return null;
        }
        for (HostelRoom room : rooms) {
            if (room != null && room.getOccupied() < room.getBeds()) {
                return room;
            }
        }
        return null;
    }

    public static void safeAllot(HostelRoom[] rooms, String studentName) {
        HostelRoom available = findAvailableRoom(rooms);
        if (available != null) {
            available.allot(studentName);
            System.out.println(studentName + " allotted to room " + available.getRoomNo());
        } else {
            System.out.println("No rooms available for " + studentName);
        }
    }

    public static void main(String[] args) {
        HostelRoom[] testRooms1 = {
            new HostelRoom("C-214", 3, 2),
            new HostelRoom("C-507", 2, 2)
        };
        safeAllot(testRooms1, "Divya");

        HostelRoom[] testRooms2 = {
            new HostelRoom("C-214", 3, 3),
            new HostelRoom("C-507", 2, 2)
        };
        safeAllot(testRooms2, "Divya");
    }
}