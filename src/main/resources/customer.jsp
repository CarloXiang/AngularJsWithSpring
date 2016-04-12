Hello: {{myName}}
<table class="table table-striped">
    <tr>
        <th data-ng-click="doSort('name')">Name</th>
        <th data-ng-click="doSort('city')">City</th>
        <th data-ng-click="doSort('orderTotal')">Order Total</th>
        <th data-ng-click="doSort('joined')">Joined</th>
        <th>&nbsp;</th>
        <th>Delete</th>
    </tr>

    <tr data-ng-repeat="cust in customers | limitTo:4 | filter:customerFilter | orderBy:sortBy:reverse">
        <td>{{cust.name | uppercase}}</td>
        <td>{{cust.city}}</td>
        <td>{{cust.orderTotal | currency}}</td>
        <td>{{cust.joined | date: 'dd-MMM-yyyy'}}</td>
        <td><a href= "#/orders/{{cust.id}}">View Order ({{cust.id}})</a></td>
        <td class="center"><span class="glyphicon glyphicon-remove delete" data-ng-click="deleteCustomer(cust.id)"></span></td>
    </tr>

</table>