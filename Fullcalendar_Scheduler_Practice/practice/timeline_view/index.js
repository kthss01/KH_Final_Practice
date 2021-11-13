document.addEventListener('DOMContentLoaded', function() {
  var calendarEl = document.getElementById('calendar');

  var calendar = new FullCalendar.Calendar(calendarEl, {
    editable: true,
    selectable: true,
    headerToolbar: {
      left: 'today prev,next',
      center: 'title',
      right: 'resourceTimelineDay,resourceTimelineWeek'
    },
    aspectRatio: 1.6,
    initialView: 'resourceTimelineDay',
    resourceGroupField: 'building',
    resources: [
      { id: 'a', building: '460 Bryant', title: 'Auditorium A', eventBackgroundColor: 'red', 
        extendedProps : {
          color: 'yellow',
        },
      },
      { id: 'b', building: '460 Bryant', title: 'Auditorium B' },
      { id: 'c', building: '460 Bryant', title: 'Auditorium C' },
      { id: 'd', building: '460 Bryant', title: 'Auditorium D' },
      { id: 'e', building: '460 Bryant', title: 'Auditorium E' },
      { id: 'f', building: '460 Bryant', title: 'Auditorium F' },
      { id: 'g', building: '564 Pacific', title: 'Auditorium G' },
      { id: 'h', building: '564 Pacific', title: 'Auditorium H' },
      { id: 'i', building: '564 Pacific', title: 'Auditorium I' },
      { id: 'j', building: '564 Pacific', title: 'Auditorium J' },
      { id: 'k', building: '564 Pacific', title: 'Auditorium K' },
      { id: 'l', building: '564 Pacific', title: 'Auditorium L' },
      { id: 'm', building: '564 Pacific', title: 'Auditorium M' },
      { id: 'n', building: '564 Pacific', title: 'Auditorium N' },
      { id: 'o', building: '101 Main St', title: 'Auditorium O' },
      { id: 'p', building: '101 Main St', title: 'Auditorium P' },
      { id: 'q', building: '101 Main St', title: 'Auditorium Q' },
      { id: 'r', building: '101 Main St', title: 'Auditorium R' },
      { id: 's', building: '101 Main St', title: 'Auditorium S' },
      { id: 't', building: '101 Main St', title: 'Auditorium T' },
      { id: 'u', building: '101 Main St', title: 'Auditorium U' },
      { id: 'v', building: '101 Main St', title: 'Auditorium V' },
      { id: 'w', building: '101 Main St', title: 'Auditorium W' },
      { id: 'x', building: '101 Main St', title: 'Auditorium X' },
      { id: 'y', building: '101 Main St', title: 'Auditorium Y' },
      { id: 'z', building: '101 Main St', title: 'Auditorium Z' }
    ],
    select: function(info) {
      console.log('select', info);
    },
    eventClick: function(info) {
      console.log('eventClick', info);
      console.log(info.event);
    },
    // dateClick: function(info) {
    //   console.log('dateClick', info);
    //   if (info.jsEvent.target.classList.contains('fc-bg-event')) {
    //     console.log('click background');
    //   }
    // }
    // background event도 클릭할 수 있게 해줌
    selectOverlap: function(event) {
      // if (event.rendering === 'background') {
      //   console.log('background click');
      // }
      return event.rendering === 'background';
    },
    resourceGroupLabelDidMount: function(arg) {
      // console.log(arg);
      // arg.el.id = 'a';
      arg.el.addEventListener('click', function() {
        console.log(arg)
        // console.log(arg.resource) // 여긴 resource 없음
        console.log(arg.groupValue)
        // console.log(arg.el.id);
        // arg.groupValue += '1';
        console.log(arg.el.querySelector('.fc-datagrid-cell-main'));
        arg.el.querySelector('.fc-datagrid-cell-main').textContent = 1;
      })
    },
    // resourceGroupLabelContent: function(arg) {
    //   console.log('group', arg);
    // },
    resourceLabelDidMount: function(arg) {
      arg.el.addEventListener('click', function() {
        // console.log(arg)
        // console.log(arg.resource)
        console.log(arg.resource.id)
        console.log(arg.resource.title)
        console.log(arg.resource.eventBackgroundColor)
      })
    },
  });

  console.log(calendar.getResources());

  let resource = calendar.getResourceById('a');
  console.log(resource.resourceEditable);
  // resource.setProp("resourceEditable", true);
  console.log(resource);
  
  calendar.render();

  calendar.addEvent({
    id: 'a',
    title: 'test',
    start: '2021-11-10',
    // backgroundColor: 'green',
  });

  calendar.getEventById('a').setResources([resource]);

  // console.log(calendar.getEvents()[0]);
  // console.log(calendar.getEvents()[0].resourceIds); // 안먹히는듯

  resource.eventBackgroundColor = 'green'; // 이렇게 바꿔도 안됨
  // calendar.refetchResources(); // 이건 무슨 기능인지 모르겠음
  
  resource = calendar.getResourceById('a')
  console.log(resource.extendedProps.color);

  resource.setExtendedProp('color', 'orange');

  console.log(resource.extendedProps.color);

  //resource.remove(); // 이벤트도 같이 지워짐

  // resource.setProp('eventBackgroundColor', 'green');
  // resource.setProp('eventBorderColor', 'green');
  // resource.setProp('eventTextColor', 'green');
  // resource.setProp('eventColor', 'green');
  // resource.setProp('title', 'green');
  // console.log(resource);

  // console.log(calendar.getResourceById('a').eventBackgroundColor)
  // console.log(calendar.getResourceById('a').eventBorderColor)
  // console.log(calendar.getResourceById('a').eventTextColor)
  // console.log(calendar.getResourceById('a').eventColor)

  // console.log(calendar.getResourceById('a').getEvents());

  ////////////////////

  calendar.addEvent({
    id: 'b',
    title: 'test',
    start: '2021-11-11',
    // backgroundColor: 'green',
    resourceId: 'a',
  });

  calendar.addEvent({
    id: 'c',
    title: 'test',
    start: '2021-11-11',
    // backgroundColor: 'green',
    resourceId: 'c',
    display: 'background',
    overlap: false,
  });
});