<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<!-- <link rel="styles" type="text/css" href="path/to/styles.css">  -->
<!--  <link rel='stylesheet' href='<c:url value="/resources/webapp/css/styles.css" />' /> -->
<c:url value="/css/style.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet">
<c:url value="/pictures/Gamer2.png" var="Gamer2" />
</head>
<body>
	<div class="font">

		<jsp:include page="header.jsp" />

		<br></br>
		<hr>





		<!---Main images and title------------------------------------------------------------------>
		<div class="main-photo-box"></div>
		<div class="author-photo-box">
			<img class="author" src="${Gamer2}">
		</div>
		<div class="name-box">by Maciej Gomulec</div>

		<!--Main blog----<img class="name" src="../images/nazwa2.png">--------------------------------------------------------------->

		<div class="main-container">
			<div class="blog-container">
				<div class="blog-entry b1">
					<div class="blog-image"></div>
					<div class="blog-text-box">
						<div class="title">
							<a>
								<h1 class="big-title-font">God of War Ragnarok Review</h1>
							</a>
							<h2 class="subtitle subtitle-font">A captivating epic filled
								with heart</h2>
						</div>
						<div class="contents">
							<p>The most powerful weapon God of War Ragnarok wields isn’t
								the Leviathan Axe, which Kratos uses to cleave through enemies
								of all sorts in bloody combat. And it isn’t the awe-inspiring
								set pieces, of which there are many, that feature towering
								beasts and vast landscapes that make you quiver at the very
								sight. All those things are remarkable, but what will really
								take hold of you in Ragnarok are the excellent characters. The
								strong writing. And the nuanced drama and relationships between
								mythical people that somehow feels real, believable, and earned.
							</p>
						</div>

					</div>
				</div>
				<div class="blog-entry b2">
					<div class="blog-image"></div>
					<div class="blog-text-box">
						<div class="title">
							<a>
								<h1 class="big-title-font">World of Warcraft Review</h1>
							</a>
							<h2 class="subtitle subtitle-font">Blizzard does it again</h2>
						</div>
						<div class="contents">
							<p>MMOs are a strange beast. They are designed to make you
								play as much as possible, yet addictiveness does not always
								equal fun. In the field of pyschology, there are several kinds
								of rewards systems, and the one that seems to be the most
								successful is the random reward introduced at a random time.
								Sometimes you click the button, and nothing happens. Sometimes
								you click and get the food pellet. It's this mechanism that
								fuels the slots in Vegas, and when you walk away empty, as is
								statistically inevitable over a long enough stretch of time, you
								tell yourself that the overall value was the experience itself,
								since you come away with nothing tangible. MMOs take away your
								time and they never deliver a discreet conclusion.</p>
						</div>

					</div>
				</div>
				<div class="blog-entry b3">
					<div class="blog-image"></div>
					<div class="blog-text-box">
						<div class="title">
							<a>
								<h1 class="big-title-font">Witcher 3 Review</h1>
							</a>
							<h2 class="subtitle subtitle-font">The dark places of the
								land are full of the habitations of violence.</h2>
						</div>
						<div class="contents">
							<p>Unlike its predecessor, The Witcher 3: Wild Hunt doesn't
								exactly come screaming off the starting line. Compared to The
								Witcher 2, where you're immediately plunged headlong into a sexy
								story of intrigue and betrayal, this main quest can seem
								mundane, even perfunctory at times. But each time I stepped off
								the well-beaten path to blaze my own trail, it turned into a
								wild, open, exhilarating fantasy roleplaying experience, rife
								with opportunities to make use of its excellent combat. Even
								after over 100 hours with The Witcher 3, it still tempts me to
								press on – there’s so much more I want to learn, and hunt.</p>
						</div>

					</div>
				</div>

				<div class="load-older">
					<a class="money-link">Show Previous</a>
				</div>
			</div>

			<!--Side menu - recents------------------------------------------------------------>

			<div class="side-menu-container">
				<div class="title s-title">
					<a>Highest Rated</a>
				</div>
				<div class="recent-box">
					<div class="side-box r1">
						<div class="side-title">
							<a>
								<h2 class="sidemenu-subtitle-font">God of War Ragnarok</h2>
							</a>
						</div>
						<div class="side-image"></div>
					</div>
					<div class="side-box r2">
						<div class="side-title">
							<a>
								<h2 class="sidemenu-subtitle-font">World of Warcraft</h2>
							</a>
						</div>
						<div class="side-image"></div>
					</div>
					<div class="side-box r3">
						<div class="side-title">
							<a>
								<h2 class="sidemenu-subtitle-font">Witcher 3</h2>
							</a>
						</div>
						<div class="side-image"></div>
					</div>
				</div>

				<!---Side menu - most popular-------------------------------------------------->

				<div class="popular-container">
					<div class="title s-title">
						<a>Most Popular</a>
					</div>
					<div class="popular-box">
						<div class="side-box p1">
							<div class="side-title">
								<a>
									<h2 class="sidemenu-subtitle-font">God of War Ragnarok</h2>
								</a>
							</div>
							<div class="side-image"></div>
						</div>
					</div>
					<div class="popular-box">
						<div class="side-box p2">
							<div class="side-title">
								<a>
									<h2 class="sidemenu-subtitle-font">Witcher 3</h2>
								</a>
							</div>
							<div class="side-image"></div>
						</div>
					</div>
				</div>
			</div>
		</div>



		<div class="support-text">
			<p>IF YOU LIKE IT SUPPORT MY WORK!!</p>
		</div>
		<div class="support-box">
			<a class="money-link"><img class="money"
				src="../images/money2.png"></a>
		</div>

		<!--Footer------------------------------------------------------------------->


		<jsp:include page="footer.jsp" />
	</div>


</body>
</html>