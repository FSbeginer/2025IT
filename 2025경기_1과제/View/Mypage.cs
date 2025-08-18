using _2025경기_1과제.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Drawing.Drawing2D;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace _2025경기_1과제.View
{
    public partial class Mypage : _2025경기_1과제.Template.BF
    {
        public Mypage()
        {
            InitializeComponent();
        }

        private void panel1_Paint(object sender, PaintEventArgs e)
        {
            using (var path = new GraphicsPath())
            {
                path.StartFigure();
                path.AddEllipse(panel1.ClientRectangle);
                panel1.Region = new Region(path);
            }
        }
        List<reservation> list = new List<reservation>();
        private void Mypage_Load(object sender, EventArgs e)
        {
            label1.Text = Hp.user.name + "(" + Hp.GetAge(Hp.user.birth) + "세)";
            dataLoad();
        }

        private void dataLoad()
        {
            dataGridView1.Rows.Clear();
            list.Clear();
            using (var db = new Model.SkillCinemaEntities())
            {
                var rlist = db.reservation.Where(x => x.uno == Hp.user.uno).OrderBy(x => x.rdate).ToList();
                label2.Text = $"총 액: {rlist.Sum(x => x.price):N0}";
                label3.Text = $"총 예매건수:{rlist.Count}건";
                foreach (var item in rlist)
                {
                    int n = db.review.Where(x => x.rno == item.rno).Select(x => x.likecount).FirstOrDefault();
                    dataGridView1.Rows.Add(item.rdate.ToShortDateString(), item.movie.mname, item.movie.genre.gname, item.price, item.seat, n == 0 ? "" : n + "");
                    list.Add(item);
                }
            }
        }

        private void dataGridView1_RowHeaderMouseClick(object sender, DataGridViewCellMouseEventArgs e)
        {
            dataGridView1.RowsDefaultCellStyle.SelectionBackColor = Color.Yellow;
            dataGridView1.RowsDefaultCellStyle.SelectionForeColor = Color.Black;
        }

        private void 별점수정ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            int star = 0;
            var v = new View.별점();
            v.FormClosed += (s, e2) =>
            {
                star = int.Parse(v.label1.Text.Substring(0, 1));
            };
            ShowPage(v);
            using (var db = new Model.SkillCinemaEntities())
            {
                var r = list[dataGridView1.CurrentRow.Index];
                db.reservation.Attach(r);
                if(별점수정ToolStripMenuItem.Text == "별점등록")
                {
                    review rv=  new review();
                    rv.rno = r.rno;
                    rv.likecount = star;
                    db.review.Add(rv);
                }
                else
                {
                    review rv = db.review.Where(x=>x.rno == r.rno).FirstOrDefault();
                    rv.likecount = star;
                }
                db.SaveChanges();
                dataLoad();
            }
        }

        private void 삭제ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            var r = list[dataGridView1.CurrentRow.Index];
            using (var db = new Model.SkillCinemaEntities())
            {
                db.users.Attach(Hp.user);
                if (삭제ToolStripMenuItem.Text == "취소")
                {
                    Hp.user.seatcount -= r.seat.Split(',').Length;
                }
                db.reservation.Attach(r);
                db.reservation.Remove(r);
                db.SaveChanges();
                dataLoad();
            }
        }

        private void contextMenuStrip1_Opening(object sender, CancelEventArgs e)
        {
            if (dataGridView1.RowsDefaultCellStyle.SelectionBackColor != Color.Yellow)
            {
                e.Cancel = true;
                return;
            }

            using (var db = new Model.SkillCinemaEntities())
            {
                var r = list[dataGridView1.CurrentRow.Index];
                if (DateTime.Now < r.rdate)
                {
                    별점수정ToolStripMenuItem.Visible = false;
                    삭제ToolStripMenuItem.Text = "취소";
                }
                else
                {
                    var rv = db.review.FirstOrDefault(x => x.rno == r.rno);
                    별점수정ToolStripMenuItem.Visible = true;
                    if (rv == default)
                    {

                        별점수정ToolStripMenuItem.Text = "별점등록";
                        삭제ToolStripMenuItem.Text = "삭제";
                    }
                    else
                    {
                        별점수정ToolStripMenuItem.Text = "별점수정";
                        삭제ToolStripMenuItem.Text = "삭제";
                    }
                }
            }
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            dataGridView1.RowsDefaultCellStyle.SelectionBackColor = SystemColors.Highlight;
            dataGridView1.RowsDefaultCellStyle.SelectionForeColor = SystemColors.HighlightText;
        }
    }
}
