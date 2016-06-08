## javamasについて
Java開発をもっとできるようになりたいという思いから、数名が集まって始められたプロジェクト。

## この文書について
javamasのレポジトリで開発を進めるにあたり、こういうことを気をつけていこうという覚書。

## 計画と実施
計画にはIssueを使用し、実施後はPull Requestを送ってレビューを受ける。

### Issueの作成
Issueを作成するのは、こんな時。
- バグを発見したので報告する
- 新機能を提案する
- 粒度が荒いIssueを、複数のIssueに分割する

Issueにはテンプレートを設定しておき、本文に挿入されるようにしてあるので、それに沿って記入する。
テンプレートの内容が実情に合わなくなったら、話し合って更新していく。

#### Issueテンプレートについて
```
## Context
このIssueが切られた背景を記述する。

## Problem, Idea
不具合を発見した場合は、何が問題なのかを記述する。
提案する場合は、その内容について記述する。
Issue分割の場合は、新しいIssueでやることを記述する。

## Solution, next step
問題に対する解決方法、新機能を実現するために次にやるべきことの一覧などを記述する。

## Goal
何を達成するとこのIssueが閉じられるか記述する。

## Issue links
関連するIssueがあれば、ここにリンクを貼る。
```


### Pull Requestの作成
機能追加、不具合改修でコードを追加、更新した場合は、Pull Requestを作成する。
Pull Requestもテンプレートを設定し、Issueのテンプレートと同様に運用していく。

#### Pull Requestテンプレートについて
```
## Purpose of this pull request
このPull Requestが作られた目的を記述する。
例えば、
> Resolve #23. Enable to connect MQ
> Fix #33. 文字列長が1000文字を越えるとエラーが発生する
など。

## Changes proposed in this pull request
どのような変更を加えたか、箇条書きにする。
> - correct host url
> - 1000文字以内に収まらない文字列は、後ろを切り捨てるように修正
など。

## Issue Links
関連するIssueへのリンクを貼る。

@javamas/javamas
全員に見てもらえるよう、チームの@-mentionを挿入する。
```
