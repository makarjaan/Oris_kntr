<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Weather Page</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        $(document).ready(function () {
            $("#weather-form").on("submit", function (event) {
                event.preventDefault();
                const city = $("input[name='city']").val();
                $.ajax({
                    url: "/main",
                    method: "POST",
                    data: { city: city },
                    success: function (response) {
                        $("#weather-result").text(response);
                    },
                    error: function () {
                        $("#weather-result").text("Error connecting to server.");
                    }
                });
            });
        });
    </script>
</head>

<body>
<h1>Weather Information</h1>
<form id="weather-form">
    <input type="text" name="city" placeholder="Enter city name">
    <button type="submit">Get Weather</button>
</form>

<div id="weather-result"></div>
</body>

</html>