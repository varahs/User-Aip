// MODULE
var DBVersionTracker = angular.module("DBVersionTracker", ["ngRoute"]);

// ROUTES
DBVersionTracker.config(function ($routeProvider) {
  $routeProvider

    .when("/", {
      templateUrl: "views/request.html",
      controller: "RequestPageController",
    })

    .when("/view", {
      templateUrl: "views/view.html",
      controller: "ViewPageController",
    })

    .when("/approver", {
      templateUrl: "views/approver.html",
      controller: "ApprovePageController",
    })

    .otherwise({
      redirectTo: "/",
    });
});

DBVersionTracker.directive('fileModel', ['$parse', function ($parse) {
    return {
       restrict: 'A',
       link: function(scope, element, attrs) {
          var model = $parse(attrs.fileModel);
          var modelSetter = model.assign;
          
          element.bind('change', function() {
             scope.$apply(function() {
                modelSetter(scope, element[0].files[0]);
             });
          });
       }
    };
 }]);
DBVersionTracker.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl) {
       var fd = new FormData();
       fd.append('file', file);
    
       $http.post(uploadUrl, fd, {
          transformRequest: angular.identity,
          headers: {'Content-Type': undefined}
       })
       .success(function() {
       })
       .error(function() {
       });
    }
 }]);

// CONTROLLERS
DBVersionTracker.controller("RequestPageController", [
  "$scope",
  "$http",
  "$location",
  "fileUpload",
  function ($scope, $http,$location,fileUpload) {
//  Initially clear Formdata
	  $scope.Formdata={};
    var now = new Date();
    
//    Toggle Executed on PU button
    $scope.Formdata.executed='No';
    $scope.toggleRadio=function(value){
 	   $scope.Formdata.executed=value;
    }
    
//    Generate Ticket Number
    timestamp =
      (now.getMonth() < 10 ? "0" : "") + (now.getMonth() + 1).toString();
    timestamp += (now.getDate() < 10 ? "0" : "") + now.getDate().toString();
    timestamp += now.getFullYear().toString().slice(2, 4);
    timestamp += (now.getHours() < 10 ? "0" : "") + now.getHours().toString();
    timestamp +=
      (now.getMinutes() < 10 ? "0" : "") + now.getMinutes().toString();
    timestamp +=
      (now.getSeconds() < 10 ? "0" : "") + now.getSeconds().toString();
    timestamp += now.getMilliseconds().toString();
    $scope.ticket_no = timestamp;
    
//    Reset Button
    $scope.reset = function () {
      console.log("Reset function called : ");
      
//      Clear Form
    $scope.Formdata = {};

    };

    $scope.uploadFile = function() {
        
        var file = $scope.myFile;
        console.log('file is ' );
        console.dir(file);
        var uploadUrl = "/upload";
        fileUpload.uploadFileToUrl(file, uploadUrl);
     };

//    Submit button
    $scope.submitForm = function () {    
      console.log("Submit form run");
      $scope.Formdata.requestId = timestamp;
      $scope.Formdata.status = "Pending";
      $scope.Formdata.approveDate = "";
      $scope.Formdata.approvedBy = "";
      $scope.Formdata.comment = "";
      var temp = new Date();
      
//      Generate Request Date
      time =
        (temp.getMonth() < 10 ? "0" : "") + (temp.getMonth() + 1).toString();
      time += (temp.getDate() < 10 ? "0" : "") + temp.getDate().toString();
      time += temp.getFullYear().toString();
      $scope.Formdata.requestDate = time;
      if($scope.myFile===undefined){
    	  $scope.Formdata.path='';
      }
      else{
    	  $scope.Formdata.path=$scope.myFile.name;
      }
      
      console.log($scope.Formdata);
      $http({
        url: "/write",
        method: "POST",
        data: angular.toJson($scope.Formdata),
        headers: {
          "Content-Type": "application/json",
        },
      }).then(
        function (response) {
        	$scope.uploadFile();
          $scope.msg = "Data Submitted Successfully!";
          alert("Request Submitted Succesfully");
          $scope.Formdata = {};
          $location.path("/view");
          $("#show").hide();
          $("#reset-btn").hide();
        },
        function (response) {
          $scope.msg = "Service not Exists";
        }
      );
      
     
    };
  },
]);

DBVersionTracker.controller("ViewPageController", [
  "$scope",
  "$http",
  "$route",
  function ($scope, $http,$route) {
	  

    $scope.requests = null;
    
//    Get requests
    $http
      .get("/read")
      .success(function (data, status) {
        console.log(data);
        $scope.requests = data;
      })
      .error(function (data, status) {
        console.log("Error ", data);
      });

    
      // Controller to download excel file

      $scope.download = function (value) {

        $scope.approver = value;
  // 	Reject only if valid approver
        $http
          .get("/read/approvers")
          .success(function (data, status) {
            var status = 0;
            for (var i = 0; i < data.length; i++) {
              if (data[i] == $scope.approver) {
                status = 1;
              }
            }
            if (status == 0) {
              alert("Approver email address is invalid");
            } else {
              console.log("Donwload request started");
              window.open("/download", '_blank', '');
              
            }
          })
          .error(function (data, status) {
            console.log("Error ", data);
          });
      };
    
  },
]);

DBVersionTracker.controller("ApprovePageController", [
  "$scope",
  "$location",
  "$http",
  "$routeParams",
  "$rootScope",
  function ($scope, $location, $http, $routeParams, $rootScope) {
//	  if user tries to enter invalid ticket number this will redirect to view page
    if ($routeParams.ticketno == undefined) {
      alert("Invalid ticket number");
      $location.path("/view");
      return;
    }

//    Get request data
    $scope.readId = function (requestId) {
      console.log("Request ID", requestId);
      $http
        .get("/read/" + requestId)
        .success(function (data, status) {
    	console.log(data);
          $scope.value = data;
        })
        .error(function (data, status) {
          console.log("Error ", data);
        });
    };

    $scope.readId($routeParams.ticketno);

// Cancel button
    $scope.cancel = function () {
      console.log("Cancel get called");
      $location.path("/view");
    };

    
// Approve Request
    $scope.approve = function (value) {
      $scope.Formdata = value;

//      Read approvers Approver only if valid approver
      $http
        .get("/read/approvers")
        .success(function (data, status) {
          var status = 0;
          for (var i = 0; i < data.length; i++) {
            if (data[i] == $scope.Formdata.approvedBy) {
              status = 1;
            }
          }
          if (status == 0) {
            alert("Approval is invalid");
          } else {
            //	$scope.Formdata = value;
            var temp = new Date();
            time =
              (temp.getMonth() < 10 ? "0" : "") +
              (temp.getMonth() + 1).toString();
            time +=
              (temp.getDate() < 10 ? "0" : "") + temp.getDate().toString();
            time += temp.getFullYear().toString();
            $scope.Formdata.status = "Approved";
            $scope.Formdata.approvedDate = time;
            var data = JSON.stringify($scope.Formdata);

//            Send Update request to server
            $http({
              url: "/update",
              method: "POST",
              data: data,
              headers: {
                "Content-Type": "application/json",
              },
            }).then(
              function (response) {
                $scope.msg = "Data Updated Successfully!";
                console.log("successful");
                alert("Request is approved");
            	$location.path("/view");
              },
              function (response) {
                $scope.msg = "Service not Exists";
              }
            );

          
          }
        })
        .error(function (data, status) {
          console.log("Error ", data);
        });
    };

    
//    Reject Button
    $scope.reject = function (value) {

      $scope.Formdata = value;
// 	Reject only if valid approver
      $http
        .get("/read/approvers")
        .success(function (data, status) {
          var status = 0;
          for (var i = 0; i < data.length; i++) {
            if (data[i] == $scope.Formdata.approvedBy) {
              status = 1;
            }
          }
          if (status == 0) {
            alert("Approval is invalid");
          } else {
            var temp = new Date();
            time =
              (temp.getMonth() < 10 ? "0" : "") +
              (temp.getMonth() + 1).toString();
            time +=
              (temp.getDate() < 10 ? "0" : "") + temp.getDate().toString();
            time += temp.getFullYear().toString();
            $scope.Formdata.status = "Rejected";
            $scope.Formdata.approvedDate = time;
//            $scope.Formdata.path  = "se2";
            var data = JSON.stringify($scope.Formdata);


            $http({
              url: "/update",
              method: "POST",
              data: angular.toJson($scope.Formdata),
              headers: {
                "Content-Type": "application/json",
              },
            }).then(
              function (response) {
                $scope.msg = "Data Updated Successfully!";
                alert("Request is Rejected");
                $location.path("/view");
              },
              function (response) {
                $scope.msg = "Service not Exists";
              }
            );
            
          }
        })
        .error(function (data, status) {
          console.log("Error ", data);
        });
    };
  },
]);
