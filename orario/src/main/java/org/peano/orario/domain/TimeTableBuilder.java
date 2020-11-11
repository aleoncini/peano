package org.peano.orario.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TimeTableBuilder {

    private int counter;

    public TimeTable build(){
        List<Timeslot> slots = this.buildTimeSlotList();
        System.out.println("t slots____________________ " + slots.size());
        List<Room> rooms = this.buildRoomList(20);
        System.out.println("rooms______________________ " + rooms.size());
        List<Lesson> lessons = this.buildLessonList();
        System.out.println("lessons____________________ " + lessons.size());
        return new TimeTable(slots, rooms, lessons);
    }

	private List<Lesson> buildLessonList() {
        List<Lesson> lessons = new ArrayList<Lesson>();
        Teacher teacher = new Teacher();

        // sezione A _________________________________________________________
        // ITALIANO
        teacher.subject = "Lingua e letteratura italiana";
        teacher.name = "Giovanni Pascoli";
        lessons.addAll(buildLessons(teacher, "Classe 1A", 4));
        lessons.addAll(buildLessons(teacher, "Classe 2A", 4));
        lessons.addAll(buildLessons(teacher, "Classe 3A", 4));
        teacher.name = "Alessandro Manzoni";
        lessons.addAll(buildLessons(teacher, "Classe 4A", 4));
        lessons.addAll(buildLessons(teacher, "Classe 5A", 4));

        // LATINO
        teacher.subject = "Lingua e letteratura latina";
        teacher.name = "Marco Tullio Cicerone";
        lessons.addAll(buildLessons(teacher, "Classe 1A", 3));
        lessons.addAll(buildLessons(teacher, "Classe 2A", 3));
        lessons.addAll(buildLessons(teacher, "Classe 3A", 3));
        teacher.name = "Tito Maccio Plauto";
        lessons.addAll(buildLessons(teacher, "Classe 4A", 3));
        lessons.addAll(buildLessons(teacher, "Classe 5A", 3));

        // INGLESE
        teacher.subject = "Lingua e cultura straniera";
        teacher.name = "Margareth Tatcher";
        lessons.addAll(buildLessons(teacher, "Classe 1A", 3));
        lessons.addAll(buildLessons(teacher, "Classe 2A", 3));
        teacher.name = "Winston Leonard Spencer Churchill";
        lessons.addAll(buildLessons(teacher, "Classe 3A", 3));
        lessons.addAll(buildLessons(teacher, "Classe 4A", 3));
        lessons.addAll(buildLessons(teacher, "Classe 5A", 3));

        // Storia e geografia
        teacher.subject = "Storia e geografia";
        teacher.name = "Cristoforo Colombo";
        lessons.addAll(buildLessons(teacher, "Classe 1A", 3));
        lessons.addAll(buildLessons(teacher, "Classe 2A", 3));

        // Storia
        teacher.subject = "Storia";
        teacher.name = "Benedetto Croce";
        lessons.addAll(buildLessons(teacher, "Classe 3A", 2));
        lessons.addAll(buildLessons(teacher, "Classe 4A", 2));
        lessons.addAll(buildLessons(teacher, "Classe 5A", 2));

        // Filosofia
        teacher.subject = "Filosofia";
        teacher.name = "Karl Marx";
        lessons.addAll(buildLessons(teacher, "Classe 3A", 3));
        lessons.addAll(buildLessons(teacher, "Classe 4A", 3));
        lessons.addAll(buildLessons(teacher, "Classe 5A", 3));

        // Scienza
        teacher.subject = "Biologia, Chimica, Scienze della terra";
        teacher.name = "Rita Levi Montalcini";
        lessons.addAll(buildLessons(teacher, "Classe 1A", 2));
        lessons.addAll(buildLessons(teacher, "Classe 2A", 2));
        teacher.name = "Margherita Hack";
        lessons.addAll(buildLessons(teacher, "Classe 3A", 3));
        lessons.addAll(buildLessons(teacher, "Classe 4A", 3));
        lessons.addAll(buildLessons(teacher, "Classe 5A", 3));
        
        // Fisica
        teacher.subject = "Fisica";
        teacher.name = "Enrico Fermi";
        lessons.addAll(buildLessons(teacher, "Classe 1A", 2));
        lessons.addAll(buildLessons(teacher, "Classe 2A", 2));
        teacher.name = "Guglielmo Marconi";
        lessons.addAll(buildLessons(teacher, "Classe 3A", 3));
        lessons.addAll(buildLessons(teacher, "Classe 4A", 3));
        lessons.addAll(buildLessons(teacher, "Classe 5A", 3));

        // Matematica
        teacher.subject = "Matematica";
        teacher.name = "Leonardo Fibonacci";
        lessons.addAll(buildLessons(teacher, "Classe 1A", 5));
        lessons.addAll(buildLessons(teacher, "Classe 2A", 5));
        lessons.addAll(buildLessons(teacher, "Classe 3A", 4));
        lessons.addAll(buildLessons(teacher, "Classe 4A", 4));
        lessons.addAll(buildLessons(teacher, "Classe 5A", 4));

        // DISEGNO
        teacher.subject = "Disegno e Storia dell'arte";
        teacher.name = "Leonardo Da Vinci";
        lessons.addAll(buildLessons(teacher, "Classe 1A", 2));
        lessons.addAll(buildLessons(teacher, "Classe 2A", 2));
        lessons.addAll(buildLessons(teacher, "Classe 3A", 2));
        lessons.addAll(buildLessons(teacher, "Classe 4A", 2));
        lessons.addAll(buildLessons(teacher, "Classe 5A", 2));

        // Ginnastica
        teacher.subject = "Scienze motorie e sportive";
        teacher.name = "Pietro Mennea";
        lessons.addAll(buildLessons(teacher, "Classe 1A", 2));
        lessons.addAll(buildLessons(teacher, "Classe 2A", 2));
        lessons.addAll(buildLessons(teacher, "Classe 3A", 2));
        lessons.addAll(buildLessons(teacher, "Classe 4A", 2));
        lessons.addAll(buildLessons(teacher, "Classe 5A", 2));

        // Religione
        teacher.subject = "Religione cattolica o attività alternative";
        teacher.name = "Don Luigi Sturzo";
        lessons.addAll(buildLessons(teacher, "Classe 1A", 1));
        lessons.addAll(buildLessons(teacher, "Classe 2A", 1));
        lessons.addAll(buildLessons(teacher, "Classe 3A", 1));
        lessons.addAll(buildLessons(teacher, "Classe 4A", 1));
        lessons.addAll(buildLessons(teacher, "Classe 5A", 1));

        // sezione B _________________________________________________________
        // ITALIANO
        teacher.subject = "Lingua e letteratura italiana";
        teacher.name = "Alessandro Manzoni";
        lessons.addAll(buildLessons(teacher, "Classe 1B", 4));
        lessons.addAll(buildLessons(teacher, "Classe 2B", 4));
        lessons.addAll(buildLessons(teacher, "Classe 3B", 4));
        teacher.name = "Giuseppe Gioacchino Belli";
        lessons.addAll(buildLessons(teacher, "Classe 4B", 4));
        lessons.addAll(buildLessons(teacher, "Classe 5B", 4));

        // LATINO
        teacher.subject = "Lingua e letteratura latina";
        teacher.name = "Publio Virgilio Marone";
        lessons.addAll(buildLessons(teacher, "Classe 1B", 3));
        lessons.addAll(buildLessons(teacher, "Classe 2B", 3));
        teacher.name = "Tito Maccio Plauto";
        lessons.addAll(buildLessons(teacher, "Classe 3B", 3));
        lessons.addAll(buildLessons(teacher, "Classe 4B", 3));
        lessons.addAll(buildLessons(teacher, "Classe 5B", 3));

        // INGLESE
        teacher.subject = "Lingua e cultura straniera";
        teacher.name = "William Shakespeare";
        lessons.addAll(buildLessons(teacher, "Classe 1B", 3));
        lessons.addAll(buildLessons(teacher, "Classe 2B", 3));
        lessons.addAll(buildLessons(teacher, "Classe 3B", 3));
        teacher.name = "George Byron";
        lessons.addAll(buildLessons(teacher, "Classe 4B", 3));
        lessons.addAll(buildLessons(teacher, "Classe 5B", 3));

        // Storia e geografia
        teacher.subject = "Storia e geografia";
        teacher.name = "Ferdinando Magellano";
        lessons.addAll(buildLessons(teacher, "Classe 1B", 3));
        lessons.addAll(buildLessons(teacher, "Classe 2B", 3));

        // Storia
        teacher.subject = "Storia";
        teacher.name = "Benedetto Croce";
        lessons.addAll(buildLessons(teacher, "Classe 3B", 2));
        lessons.addAll(buildLessons(teacher, "Classe 4B", 2));
        lessons.addAll(buildLessons(teacher, "Classe 5B", 2));

        // Filosofia
        teacher.subject = "Filosofia";
        teacher.name = "Friedrich Nietzsche";
        lessons.addAll(buildLessons(teacher, "Classe 3B", 3));
        lessons.addAll(buildLessons(teacher, "Classe 4B", 3));
        lessons.addAll(buildLessons(teacher, "Classe 5B", 3));

        // Scienza
        teacher.subject = "Biologia, Chimica, Scienze della terra";
        teacher.name = "Luigi Galvani";
        lessons.addAll(buildLessons(teacher, "Classe 1B", 2));
        lessons.addAll(buildLessons(teacher, "Classe 2B", 2));
        teacher.name = "Rita Levi Montalcini";
        lessons.addAll(buildLessons(teacher, "Classe 3B", 3));
        lessons.addAll(buildLessons(teacher, "Classe 4B", 3));
        lessons.addAll(buildLessons(teacher, "Classe 5B", 3));
        
        // Fisica
        teacher.subject = "Fisica";
        teacher.name = "Enrico Fermi";
        lessons.addAll(buildLessons(teacher, "Classe 1B", 2));
        lessons.addAll(buildLessons(teacher, "Classe 2B", 2));
        lessons.addAll(buildLessons(teacher, "Classe 3B", 3));
        teacher.name = "Ettore Majorana";
        lessons.addAll(buildLessons(teacher, "Classe 4B", 3));
        lessons.addAll(buildLessons(teacher, "Classe 5B", 3));

        // Matematica
        teacher.subject = "Matematica";
        teacher.name = "John von Neumann";
        lessons.addAll(buildLessons(teacher, "Classe 1B", 5));
        lessons.addAll(buildLessons(teacher, "Classe 2B", 5));
        lessons.addAll(buildLessons(teacher, "Classe 3B", 4));
        lessons.addAll(buildLessons(teacher, "Classe 4B", 4));
        lessons.addAll(buildLessons(teacher, "Classe 5B", 4));

        // DISEGNO
        teacher.subject = "Disegno e Storia dell'arte";
        teacher.name = "Leonardo Da Vinci";
        lessons.addAll(buildLessons(teacher, "Classe 1B", 2));
        lessons.addAll(buildLessons(teacher, "Classe 2B", 2));
        lessons.addAll(buildLessons(teacher, "Classe 3B", 2));
        lessons.addAll(buildLessons(teacher, "Classe 4B", 2));
        lessons.addAll(buildLessons(teacher, "Classe 5B", 2));

        // Ginnastica
        teacher.subject = "Scienze motorie e sportive";
        teacher.name = "Pietro Mennea";
        lessons.addAll(buildLessons(teacher, "Classe 1B", 2));
        lessons.addAll(buildLessons(teacher, "Classe 2B", 2));
        lessons.addAll(buildLessons(teacher, "Classe 3B", 2));
        lessons.addAll(buildLessons(teacher, "Classe 4B", 2));
        lessons.addAll(buildLessons(teacher, "Classe 5B", 2));

        // Religione
        teacher.subject = "Religione cattolica o attività alternative";
        teacher.name = "Don Luigi Sturzo";
        lessons.addAll(buildLessons(teacher, "Classe 1B", 1));
        lessons.addAll(buildLessons(teacher, "Classe 2B", 1));
        lessons.addAll(buildLessons(teacher, "Classe 3B", 1));
        lessons.addAll(buildLessons(teacher, "Classe 4B", 1));
        lessons.addAll(buildLessons(teacher, "Classe 5B", 1));

                // sezione C _________________________________________________________
        // ITALIANO
        teacher.subject = "Lingua e letteratura italiana";
        teacher.name = "Ugo Foscolo";
        lessons.addAll(buildLessons(teacher, "Classe 1C", 4));
        lessons.addAll(buildLessons(teacher, "Classe 2C", 4));
        lessons.addAll(buildLessons(teacher, "Classe 3C", 4));
        teacher.name = "Jacques Prevert";
        lessons.addAll(buildLessons(teacher, "Classe 4C", 4));
        lessons.addAll(buildLessons(teacher, "Classe 5C", 4));

        // LATINO
        teacher.subject = "Lingua e letteratura latina";
        teacher.name = "Marco Tullio Cicerone";
        lessons.addAll(buildLessons(teacher, "Classe 1C", 3));
        lessons.addAll(buildLessons(teacher, "Classe 2C", 3));
        lessons.addAll(buildLessons(teacher, "Classe 3C", 3));
        teacher.name = "Tito Maccio Plauto";
        lessons.addAll(buildLessons(teacher, "Classe 4C", 3));
        lessons.addAll(buildLessons(teacher, "Classe 5C", 3));

        // INGLESE
        teacher.subject = "Lingua e cultura straniera";
        teacher.name = "George Byron";
        lessons.addAll(buildLessons(teacher, "Classe 1C", 3));
        lessons.addAll(buildLessons(teacher, "Classe 2C", 3));
        teacher.name = "Charles Baudelaire";
        lessons.addAll(buildLessons(teacher, "Classe 3C", 3));
        lessons.addAll(buildLessons(teacher, "Classe 4C", 3));
        lessons.addAll(buildLessons(teacher, "Classe 5C", 3));

        // Storia e geografia
        teacher.subject = "Storia e geografia";
        teacher.name = "Cristoforo Colombo";
        lessons.addAll(buildLessons(teacher, "Classe 1C", 3));
        lessons.addAll(buildLessons(teacher, "Classe 2C", 3));

        // Storia
        teacher.subject = "Storia";
        teacher.name = "Tito Livio";
        lessons.addAll(buildLessons(teacher, "Classe 3C", 2));
        lessons.addAll(buildLessons(teacher, "Classe 4C", 2));
        lessons.addAll(buildLessons(teacher, "Classe 5C", 2));

        // Filosofia
        teacher.subject = "Filosofia";
        teacher.name = "Umberto Eco";
        lessons.addAll(buildLessons(teacher, "Classe 3C", 3));
        lessons.addAll(buildLessons(teacher, "Classe 4C", 3));
        lessons.addAll(buildLessons(teacher, "Classe 5C", 3));

        // Scienza
        teacher.subject = "Biologia, Chimica, Scienze della terra";
        teacher.name = "Luigi Galvani";
        lessons.addAll(buildLessons(teacher, "Classe 1C", 2));
        lessons.addAll(buildLessons(teacher, "Classe 2C", 2));
        teacher.name = "Charles Darwin";
        lessons.addAll(buildLessons(teacher, "Classe 3C", 3));
        lessons.addAll(buildLessons(teacher, "Classe 4C", 3));
        lessons.addAll(buildLessons(teacher, "Classe 5C", 3));
        
        // Fisica
        teacher.subject = "Fisica";
        teacher.name = "Alessandro Volta";
        lessons.addAll(buildLessons(teacher, "Classe 1C", 2));
        lessons.addAll(buildLessons(teacher, "Classe 2C", 2));
        lessons.addAll(buildLessons(teacher, "Classe 3C", 3));
        lessons.addAll(buildLessons(teacher, "Classe 4C", 3));
        lessons.addAll(buildLessons(teacher, "Classe 5C", 3));

        // Matematica
        teacher.subject = "Matematica";
        teacher.name = "Srinivasa Ramanujan";
        lessons.addAll(buildLessons(teacher, "Classe 1C", 5));
        lessons.addAll(buildLessons(teacher, "Classe 2C", 5));
        lessons.addAll(buildLessons(teacher, "Classe 3C", 4));
        lessons.addAll(buildLessons(teacher, "Classe 4C", 4));
        lessons.addAll(buildLessons(teacher, "Classe 5C", 4));

        // DISEGNO
        teacher.subject = "Disegno e Storia dell'arte";
        teacher.name = "Le Corbusier";
        lessons.addAll(buildLessons(teacher, "Classe 1C", 2));
        lessons.addAll(buildLessons(teacher, "Classe 2C", 2));
        lessons.addAll(buildLessons(teacher, "Classe 3C", 2));
        lessons.addAll(buildLessons(teacher, "Classe 4C", 2));
        lessons.addAll(buildLessons(teacher, "Classe 5C", 2));

        // Ginnastica
        teacher.subject = "Scienze motorie e sportive";
        teacher.name = "Primo Carnera";
        lessons.addAll(buildLessons(teacher, "Classe 1C", 2));
        lessons.addAll(buildLessons(teacher, "Classe 2C", 2));
        lessons.addAll(buildLessons(teacher, "Classe 3C", 2));
        lessons.addAll(buildLessons(teacher, "Classe 4C", 2));
        lessons.addAll(buildLessons(teacher, "Classe 5C", 2));

        // Religione
        teacher.subject = "Religione cattolica o attività alternative";
        teacher.name = "Don Luigi Sturzo";
        lessons.addAll(buildLessons(teacher, "Classe 1C", 1));
        lessons.addAll(buildLessons(teacher, "Classe 2C", 1));
        lessons.addAll(buildLessons(teacher, "Classe 3C", 1));
        lessons.addAll(buildLessons(teacher, "Classe 4C", 1));
        lessons.addAll(buildLessons(teacher, "Classe 5C", 1));

        // sezione D _________________________________________________________
        // ITALIANO
        teacher.subject = "Lingua e letteratura italiana";
        teacher.name = "Alessandro Manzoni";
        lessons.addAll(buildLessons(teacher, "Classe 1D", 4));
        lessons.addAll(buildLessons(teacher, "Classe 2D", 4));
        teacher.name = "Umberto Eco";
        lessons.addAll(buildLessons(teacher, "Classe 3D", 4));
        lessons.addAll(buildLessons(teacher, "Classe 4D", 4));
        lessons.addAll(buildLessons(teacher, "Classe 5D", 4));

        // LATINO
        teacher.subject = "Lingua e letteratura latina";
        teacher.name = "Seneca";
        lessons.addAll(buildLessons(teacher, "Classe 1D", 3));
        lessons.addAll(buildLessons(teacher, "Classe 2D", 3));
        lessons.addAll(buildLessons(teacher, "Classe 3D", 3));
        teacher.name = "Tito Maccio Plauto";
        lessons.addAll(buildLessons(teacher, "Classe 4D", 3));
        lessons.addAll(buildLessons(teacher, "Classe 5D", 3));

        // INGLESE
        teacher.subject = "Lingua e cultura straniera";
        teacher.name = "William Shakespeare";
        lessons.addAll(buildLessons(teacher, "Classe 1D", 3));
        lessons.addAll(buildLessons(teacher, "Classe 2D", 3));
        teacher.name = "Freddie Mercury";
        lessons.addAll(buildLessons(teacher, "Classe 3D", 3));
        lessons.addAll(buildLessons(teacher, "Classe 4D", 3));
        lessons.addAll(buildLessons(teacher, "Classe 5D", 3));

        // Storia e geografia
        teacher.subject = "Storia e geografia";
        teacher.name = "Cristoforo Colombo";
        lessons.addAll(buildLessons(teacher, "Classe 1D", 3));
        lessons.addAll(buildLessons(teacher, "Classe 2D", 3));

        // Storia
        teacher.subject = "Storia";
        teacher.name = "Benedetto Croce";
        lessons.addAll(buildLessons(teacher, "Classe 3D", 2));
        lessons.addAll(buildLessons(teacher, "Classe 4D", 2));
        lessons.addAll(buildLessons(teacher, "Classe 5D", 2));

        // Filosofia
        teacher.subject = "Filosofia";
        teacher.name = "Immanuel Kant";
        lessons.addAll(buildLessons(teacher, "Classe 3D", 3));
        lessons.addAll(buildLessons(teacher, "Classe 4D", 3));
        lessons.addAll(buildLessons(teacher, "Classe 5D", 3));

        // Scienza
        teacher.subject = "Biologia, Chimica, Scienze della terra";
        teacher.name = "Rita Levi Montalcini";
        lessons.addAll(buildLessons(teacher, "Classe 1D", 2));
        lessons.addAll(buildLessons(teacher, "Classe 2D", 2));
        teacher.name = "Gregor Mendel";
        lessons.addAll(buildLessons(teacher, "Classe 3D", 3));
        lessons.addAll(buildLessons(teacher, "Classe 4D", 3));
        lessons.addAll(buildLessons(teacher, "Classe 5D", 3));
        
        // Fisica
        teacher.subject = "Fisica";
        teacher.name = "Albert Einstein";
        lessons.addAll(buildLessons(teacher, "Classe 1D", 2));
        lessons.addAll(buildLessons(teacher, "Classe 2D", 2));
        lessons.addAll(buildLessons(teacher, "Classe 3D", 3));
        lessons.addAll(buildLessons(teacher, "Classe 4D", 3));
        lessons.addAll(buildLessons(teacher, "Classe 5D", 3));

        // Matematica
        teacher.subject = "Matematica";
        teacher.name = "Carl Friedrich Gauss";
        lessons.addAll(buildLessons(teacher, "Classe 1D", 5));
        lessons.addAll(buildLessons(teacher, "Classe 2D", 5));
        lessons.addAll(buildLessons(teacher, "Classe 3D", 4));
        lessons.addAll(buildLessons(teacher, "Classe 4D", 4));
        lessons.addAll(buildLessons(teacher, "Classe 5D", 4));

        // DISEGNO
        teacher.subject = "Disegno e Storia dell'arte";
        teacher.name = "Sandro Botticelli";
        lessons.addAll(buildLessons(teacher, "Classe 1D", 2));
        lessons.addAll(buildLessons(teacher, "Classe 2D", 2));
        lessons.addAll(buildLessons(teacher, "Classe 3D", 2));
        lessons.addAll(buildLessons(teacher, "Classe 4D", 2));
        lessons.addAll(buildLessons(teacher, "Classe 5D", 2));

        // Ginnastica
        teacher.subject = "Scienze motorie e sportive";
        teacher.name = "Pietro Mennea";
        lessons.addAll(buildLessons(teacher, "Classe 1D", 2));
        lessons.addAll(buildLessons(teacher, "Classe 2D", 2));
        lessons.addAll(buildLessons(teacher, "Classe 3D", 2));
        lessons.addAll(buildLessons(teacher, "Classe 4D", 2));
        lessons.addAll(buildLessons(teacher, "Classe 5D", 2));

        // Religione
        teacher.subject = "Religione cattolica o attività alternative";
        teacher.name = "Don Luigi Sturzo";
        lessons.addAll(buildLessons(teacher, "Classe 1D", 1));
        lessons.addAll(buildLessons(teacher, "Classe 2D", 1));
        lessons.addAll(buildLessons(teacher, "Classe 3D", 1));
        lessons.addAll(buildLessons(teacher, "Classe 4D", 1));
        lessons.addAll(buildLessons(teacher, "Classe 5D", 1));

        return lessons;
    }

	private List<Lesson> buildLessons(Teacher teacher, String studentGroup, int num) {
        List<Lesson> lessons = new ArrayList<Lesson>();

        for (int i = 0; i < num; i++) {
            lessons.add(new Lesson(teacher.subject, teacher.name, studentGroup).setInstance(++counter));
        }

        return lessons;
	}

	private List<Room> buildRoomList(int n) {
        List<Room> rooms = new ArrayList<Room>();
        for (int j = 0; j < n; j++) {
            rooms.add(new Room("Aula " + j));
        }
		return rooms;
	}

	private List<Timeslot> buildTimeSlotList() {
        List<Timeslot> slots = new ArrayList<Timeslot>();
        slots.add( new Timeslot( DayOfWeek.MONDAY, LocalTime.of(8, 0, 0), LocalTime.of(8, 50, 0)));
        slots.add( new Timeslot( DayOfWeek.MONDAY, LocalTime.of(8, 55, 0), LocalTime.of(9, 45, 0)));
        slots.add( new Timeslot( DayOfWeek.MONDAY, LocalTime.of(9, 50, 0), LocalTime.of(10, 40, 0)));
        slots.add( new Timeslot( DayOfWeek.MONDAY, LocalTime.of(11, 10, 0), LocalTime.of(12, 0, 0)));
        slots.add( new Timeslot( DayOfWeek.MONDAY, LocalTime.of(12, 5, 0), LocalTime.of(12, 55, 0)));
        slots.add( new Timeslot( DayOfWeek.MONDAY, LocalTime.of(13, 0, 0), LocalTime.of(13, 50, 0)));

        slots.add( new Timeslot( DayOfWeek.TUESDAY, LocalTime.of(8, 0, 0), LocalTime.of(8, 50, 0)));
        slots.add( new Timeslot( DayOfWeek.TUESDAY, LocalTime.of(8, 55, 0), LocalTime.of(9, 45, 0)));
        slots.add( new Timeslot( DayOfWeek.TUESDAY, LocalTime.of(9, 50, 0), LocalTime.of(10, 40, 0)));
        slots.add( new Timeslot( DayOfWeek.TUESDAY, LocalTime.of(11, 10, 0), LocalTime.of(12, 0, 0)));
        slots.add( new Timeslot( DayOfWeek.TUESDAY, LocalTime.of(12, 5, 0), LocalTime.of(12, 55, 0)));
        slots.add( new Timeslot( DayOfWeek.TUESDAY, LocalTime.of(13, 0, 0), LocalTime.of(13, 50, 0)));

        slots.add( new Timeslot( DayOfWeek.WEDNESDAY, LocalTime.of(8, 0, 0), LocalTime.of(8, 50, 0)));
        slots.add( new Timeslot( DayOfWeek.WEDNESDAY, LocalTime.of(8, 55, 0), LocalTime.of(9, 45, 0)));
        slots.add( new Timeslot( DayOfWeek.WEDNESDAY, LocalTime.of(9, 50, 0), LocalTime.of(10, 40, 0)));
        slots.add( new Timeslot( DayOfWeek.WEDNESDAY, LocalTime.of(11, 10, 0), LocalTime.of(12, 0, 0)));
        slots.add( new Timeslot( DayOfWeek.WEDNESDAY, LocalTime.of(12, 5, 0), LocalTime.of(12, 55, 0)));
        slots.add( new Timeslot( DayOfWeek.WEDNESDAY, LocalTime.of(13, 0, 0), LocalTime.of(13, 50, 0)));

        slots.add( new Timeslot( DayOfWeek.THURSDAY, LocalTime.of(8, 0, 0), LocalTime.of(8, 50, 0)));
        slots.add( new Timeslot( DayOfWeek.THURSDAY, LocalTime.of(8, 55, 0), LocalTime.of(9, 45, 0)));
        slots.add( new Timeslot( DayOfWeek.THURSDAY, LocalTime.of(9, 50, 0), LocalTime.of(10, 40, 0)));
        slots.add( new Timeslot( DayOfWeek.THURSDAY, LocalTime.of(11, 10, 0), LocalTime.of(12, 0, 0)));
        slots.add( new Timeslot( DayOfWeek.THURSDAY, LocalTime.of(12, 5, 0), LocalTime.of(12, 55, 0)));
        slots.add( new Timeslot( DayOfWeek.THURSDAY, LocalTime.of(13, 0, 0), LocalTime.of(13, 50, 0)));

        slots.add( new Timeslot( DayOfWeek.THURSDAY, LocalTime.of(8, 0, 0), LocalTime.of(8, 50, 0)));
        slots.add( new Timeslot( DayOfWeek.THURSDAY, LocalTime.of(8, 55, 0), LocalTime.of(9, 45, 0)));
        slots.add( new Timeslot( DayOfWeek.THURSDAY, LocalTime.of(9, 50, 0), LocalTime.of(10, 40, 0)));
        slots.add( new Timeslot( DayOfWeek.THURSDAY, LocalTime.of(11, 10, 0), LocalTime.of(12, 0, 0)));
        slots.add( new Timeslot( DayOfWeek.THURSDAY, LocalTime.of(12, 5, 0), LocalTime.of(12, 55, 0)));
        slots.add( new Timeslot( DayOfWeek.THURSDAY, LocalTime.of(13, 0, 0), LocalTime.of(13, 50, 0)));
        return slots;
	}
}
