// ═══════════════════════════════════════════════════════════
//  GITA MENTOR — SHARED UTILITIES
// ═══════════════════════════════════════════════════════════

const API_BASE = 'http://localhost:8080';

// ── Session Helpers ─────────────────────────────────────────
const Session = {
  save(userId, username) {
    sessionStorage.setItem('gm_userId',   userId);
    sessionStorage.setItem('gm_username', username);
  },
  getUserId()   { return sessionStorage.getItem('gm_userId'); },
  getUsername() { return sessionStorage.getItem('gm_username'); },
  isLoggedIn()  { return !!sessionStorage.getItem('gm_userId'); },
  clear()       {
    sessionStorage.removeItem('gm_userId');
    sessionStorage.removeItem('gm_username');
  }
};

// ── API Helper ──────────────────────────────────────────────
async function apiPost(endpoint, data) {
  const res = await fetch(API_BASE + endpoint, {
    method:  'POST',
    headers: { 'Content-Type': 'application/json' },
    body:    JSON.stringify(data)
  });
  return res.json();
}

async function apiGet(endpoint) {
  const res = await fetch(API_BASE + endpoint);
  return res.json();
}

// ── Guard: redirect to login if not logged in ───────────────
function requireAuth() {
  if (!Session.isLoggedIn()) {
    window.location.href = 'login.html';
  }
}

// ── Show navbar username + logout ───────────────────────────
function initNavbar() {
  const usernameEl = document.getElementById('nav-username');
  const logoutBtn  = document.getElementById('logout-btn');

  if (usernameEl && Session.getUsername()) {
    usernameEl.textContent = '🙏 ' + Session.getUsername();
  }

  if (logoutBtn) {
    logoutBtn.addEventListener('click', () => {
      Session.clear();
      window.location.href = 'login.html';
    });
  }
}

// ── Show/hide alert ─────────────────────────────────────────
function showAlert(elementId, message, type = 'info') {
  const el = document.getElementById(elementId);
  if (!el) return;
  el.textContent  = message;
  el.className    = `alert alert-${type} show`;
  setTimeout(() => el.classList.remove('show'), 5000);
}
