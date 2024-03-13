//release.config.js
module.exports = {
    // 在什麼分支下，要執行 semantic-release
    branches: ['main'],
    plugins: [
      '@semantic-release/commit-analyzer',//分析你的 commit message,有沒有 feat or fix, 只有這兩個跟Version有關
      '@semantic-release/release-notes-generator',
      ["@semantic-release/exec", {
            verifyReleaseCmd: 'mvn versions:set -DnewVersion="${nextRelease.version}" -DgenerateBackupPoms=false && echo "NEXT_VERSION=${nextRelease.version}" >> build.env'
        }],
      [
        '@semantic-release/changelog', //產生CHANGELOG
        {
          mangle: false,
          headerIds: false,
          changelogFile: 'document/CHANGELOG.md', //設定檔案名稱
        },
      ],
      [
        '@semantic-release/git', // 新增CHANGELOG.md後，進行commit
        {
          assets: ['document/CHANGELOG.md', 'pom.xml', 'build.env'], // commit 要加入的檔案
          message:
            'chore(release): ${nextRelease.version} [skip ci]', //commit 時的 message
        },
      ]
      ],
  };
