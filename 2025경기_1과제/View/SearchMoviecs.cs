using _2025경기_1과제.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace _2025경기_1과제.View
{
    public partial class SearchMoviecs : _2025경기_1과제.Template.BF
    {
        List<movie> movies = new List<movie>();
        public SearchMoviecs()
        {
            InitializeComponent();
        }

        private void SearchMoviecs_Load(object sender, EventArgs e)
        {
            comboBox1.SelectedIndex = 0;
            loadData();
        }

        private void loadData()
        {
            movies.Clear();
            dataGridView1.Rows.Clear();

            using (var db = new Model.SkillCinemaEntities())
            {
                string title = textBox1.Text.Replace(" ", "");
                var list = db.movie.AsEnumerable().Where(x =>
                {
                    bool cmb = comboBox1.SelectedIndex == 0 ?
                    true : comboBox1.SelectedIndex == 1 ?
                    x.edate < DateTime.Now.Date : comboBox1.SelectedIndex == 2 ? 
                    x.edate >= DateTime.Now.Date && x.sdate <= DateTime.Now.Date : x.sdate > DateTime.Now.Date;
                    string t = x.mname.Replace(" ", "");
                    return t.Contains(title) && cmb;
                }).ToList();
                foreach (var item in list)
                {
                    dataGridView1.Rows.Add(false, item.mname, item.genre.gname, item.sdate.ToShortDateString(), (item.edate - item.sdate).Days, (item.edate >= DateTime.Now.Date? "D+"+(item.edate- DateTime.Now.Date).Days : item.sdate > DateTime.Now.Date? "D-"+(item.sdate-DateTime.Now.Date).Days:"0"));
                }
                movies.AddRange(list);
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            loadData();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            List<movie> list = new List<movie>();
            foreach (DataGridViewRow row in dataGridView1.Rows)
            {
                bool chk = Convert.ToBoolean(row.Cells[0].Value);
                if (chk)
                {
                    list.Add(movies[row.Index]);
                }
            }
            if(list.Count == 0)
            {
                msgErr("선택된 영화가 없습니다.");

            }
            else
            {
                ShowPage(new 분석 { movies = list });
            }

        }
    }
}
