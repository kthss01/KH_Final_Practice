document.addEventListener('DOMContentLoaded', function() {
  var calendarEl = document.getElementById('calendar');

  var calendar = new FullCalendar.Calendar(calendarEl, {
    headerToolbar: {
      left: 'today prev,next',
      center: 'title',
      right: 'resourceTimelineDay,resourceTimelineWeek'
    },
    initialView: 'resourceTimelineDay',
    aspectRatio: 1.5,
    resourceAreaColumns: [
      {
        field: 'title',
        headerContent: 'Room'
      },
      {
        field: 'occupancy',
        headerContent: 'Occupancy'
      }
    ],
    resources: [
      { id: 'a', title: 'Auditorium A', occupancy: 40 },
      { id: 'b', title: 'Auditorium B', occupancy: 60 },
      { id: 'c', title: 'Auditorium C', occupancy: 40 },
      { id: 'd', title: 'Auditorium D', occupancy: 40 },
      { id: 'e', title: 'Auditorium E', occupancy: 60 },
      { id: 'f', title: 'Auditorium F', occupancy: 60 },
      { id: 'g', title: 'Auditorium G', occupancy: 60 },
      { id: 'h', title: 'Auditorium H', occupancy: 40 },
      { id: 'i', title: 'Auditorium I', occupancy: 70 },
      { id: 'j', title: 'Auditorium J', occupancy: 70 },
      { id: 'k', title: 'Auditorium K', occupancy: 70 },
      { id: 'l', title: 'Auditorium L', occupancy: 75 },
      { id: 'm', title: 'Auditorium M', occupancy: 40 },
      { id: 'n', title: 'Auditorium N', occupancy: 40 },
      { id: 'o', title: 'Auditorium O', occupancy: 40 },
      { id: 'p', title: 'Auditorium P', occupancy: 40 },
      { id: 'q', title: 'Auditorium Q', occupancy: 40 },
      { id: 'r', title: 'Auditorium R', occupancy: 40 },
      { id: 's', title: 'Auditorium S', occupancy: 40 },
      { id: 't', title: 'Auditorium T', occupancy: 40 },
      { id: 'u', title: 'Auditorium U', occupancy: 40 },
      { id: 'v', title: 'Auditorium V', occupancy: 40 },
      { id: 'w', title: 'Auditorium W', occupancy: 40 },
      { id: 'x', title: 'Auditorium X', occupancy: 40 },
      { id: 'y', title: 'Auditorium Y', occupancy: 40 },
      { id: 'z', title: 'Auditorium Z', occupancy: 40 }
    ]
  });

  calendar.render();
});