using _2025경기_1과제.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Xml.Linq;

namespace _2025경기_1과제.View
{
    public partial class 분석 : _2025경기_1과제.Template.BF
    {
        public List<movie> movies{ get; set; }

        public 분석()
        {
            InitializeComponent();
        }

        private void 분석_Load(object sender, EventArgs e)
        {
            var max = movies.Max(x => (x.edate - x.sdate).Days)+1;
            for (int i = 0; i < max; i++)
            {
                dataGridView1.Columns.Add(i+1+"일차", i + 1 + "일차");
            }
            using (var db = new Model.SkillCinemaEntities())
            {
                for (global::System.Int32 i = 0; i < movies.Count; i++)
                {
                    var result = db.reservation.AsEnumerable().Where(x => x.mno == movies[i].mno).OrderBy(x=>x.mno).GroupBy(x=>x.rdate).Select(x=>x.Count()+"").Cast<object>().ToList();
                    result.Insert(0,(Boolean)(false));
                    result.Insert(1, movies[i].mname);
                    while(result.Count() != max + 2)
                    {
                        result.Add(0);
                    }
                    dataGridView1.Rows.Add(result.ToArray());
                }
            }

        }

        private void bind(List<movie> list)
        {
            chart1.Series.Clear();
            using (var db = new Model.SkillCinemaEntities())
            {
                List<int> ints = list.Select(x=>x.mno).ToList();
                var data = db.reservation.Where(x=>ints.Contains(x.mno)).GroupBy(x=>x.mno).ToList();
                for (global::System.Int32 i = 0; i < data.Count(); i++)
                {
                    var g = data[i].First();
                    chart1.Series.Add(g.movie.mname);
                    chart1.Series[i].ChartType = System.Windows.Forms.DataVisualization.Charting.SeriesChartType.Line;
                    var result = data[i].GroupBy(x=>x.rdate).Select(x=>x.Count()).ToList();
                    chart1.Series[i].Points.DataBindY(result.ToArray());
                }
            }
        }

        private void dataGridView1_CellValueChanged(object sender, DataGridViewCellEventArgs e)
        {

            List<movie> list = new List<movie>();
            foreach (DataGridViewRow row in dataGridView1.Rows)
            {
                bool chk = (bool)row.Cells[0].Value;
                if (chk)
                {
                    list.Add(movies[row.Index]);
                }
            }
            bind(list);
        }

        private void dataGridView1_CurrentCellDirtyStateChanged(object sender, EventArgs e)
        {
            if (dataGridView1.IsCurrentCellDirty)
            {
                dataGridView1.CommitEdit(DataGridViewDataErrorContexts.Commit);
            }
        }
    }
}
