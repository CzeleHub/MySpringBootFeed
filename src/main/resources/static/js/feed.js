
async function getPhoronixFeed() {
    let response;
    let html;
    let url = "https://www.phoronix.com/";

    response = await fetch(`/site?url=${encodeURIComponent(url)}`);
    
    html = await response.text();

    if (!response.ok) {
        document.documentElement.innerHTML = html;
    } else {
        document.getElementById('feed-container').innerHTML = html;
    }
    
}

async function getRssFeed() {
    let response;
    let html;
    let url = "https://www.gamingonlinux.com/article_rss.php";

    response = await fetch(`/rss?url=${encodeURIComponent(url)}`);
    
    html = await response.text();

    if (!response.ok) {
        document.documentElement.innerHTML = html;
    } else {
        document.getElementById('feed-container').innerHTML = html;
    }
    
}



function sortFeed() {
    const feed = document.getElementById("feed-container");
    const articles = Array.from(feed.querySelectorAll(".article"));

    articles.sort((a, b) => {
        return Number(b.dataset.com) - Number(a.dataset.com);
    });

    articles.forEach(article => feed.appendChild(article));
}