daemonize = false
pidfile = "/var/run/prosody/prosody.pid"

admins = { }

modules_enabled = {
  "roster",
  "saslauth",
  "tls",
  "version",
  "uptime",
  "time",
  "ping",
  "posix"
}

modules_disabled = { "s2s" }

c2s_require_encryption = true
c2s_ports = { 5222 }

allow_registration = false
authentication = "internal_plain"

use_ipv6 = false

log = { "*console" }

http_default_host = "default"

-- Set the correct domain name and certificates here
VirtualHost "example.com" 
ssl = {
    key = "/etc/prosody/certs/example.com.key",
    certificate = "/etc/prosody/certs/example.com.crt"
}
